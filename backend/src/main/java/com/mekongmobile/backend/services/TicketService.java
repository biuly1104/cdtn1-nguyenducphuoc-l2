
package com.mekongmobile.backend.services;

import com.mekongmobile.backend.*;
import com.mekongmobile.backend.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final DeviceRepository deviceRepository;
    private final CustomerRepository customerRepository;
    private final IssueCategoryRepository categoryRepository;

    public TicketService(
            TicketRepository ticketRepository,
            DeviceRepository deviceRepository,
            CustomerRepository customerRepository,
            IssueCategoryRepository categoryRepository) {
        this.ticketRepository = ticketRepository;
        this.deviceRepository = deviceRepository;
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
    }

    // Lay danh sach phieu bao hanh
    public List<Map<String, Object>> getAllTickets() {
        return ticketRepository.findAllByOrderByReceivedAtDesc()
                .stream()
                .map(this::toTicketView)
                .toList();
    }

    // Lay chi tiet phieu bao hanh
    public Map<String, Object> getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Khong tim thay phieu bao hanh"));

        return toTicketView(ticket);
    }

    // Tao phieu bao hanh
    @Transactional
    public Map<String, Object> createTicket(
            Long customerId,
            String deviceName,
            String deviceType,
            String serialNumber,
            Long categoryId,
            String description,
            String priority) {

        if (customerId == null ||
                !customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Khach hang khong ton tai");
        }

        if (categoryId == null ||
                !categoryRepository.existsById(categoryId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Nhom su co khong ton tai");
        }

        if (isBlank(deviceName) ||
                isBlank(deviceType) ||
                isBlank(serialNumber) ||
                isBlank(description)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Thong tin thiet bi va mo ta loi khong duoc de trong");
        }

        if (deviceName.length() > 100 ||
                deviceType.length() > 50 ||
                serialNumber.length() > 100 ||
                description.length() > 1000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Du lieu vuot qua do dai cho phep");
        }

        if (priority == null ||
                !List.of("THAP", "TRUNG_BINH", "CAO").contains(priority)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Muc uu tien khong hop le");
        }

        Device device = new Device();
        device.setCustomerId(customerId);
        device.setDeviceName(deviceName.trim());
        device.setDeviceType(deviceType.trim());
        device.setSerialNumber(serialNumber.trim());

        Device savedDevice = deviceRepository.save(device);

        Ticket ticket = new Ticket();
        ticket.setTicketCode(
                "BH-" + UUID.randomUUID().toString()
                        .replace("-", "")
                        .substring(0, 16)
                        .toUpperCase());

        ticket.setDeviceId(savedDevice.getDeviceId());
        ticket.setCategoryId(categoryId);
        ticket.setDescription(description.trim());
        ticket.setPriority(priority);
        ticket.setStatus("MOI_TIEP_NHAN");

        Ticket savedTicket = ticketRepository.saveAndFlush(ticket);

        return toTicketView(savedTicket);
    }

    // Cap nhat phieu bao hanh
    @Transactional
    public Map<String, Object> updateTicket(
            Long id,
            Long categoryId,
            String description,
            String priority) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Khong tim thay phieu bao hanh"));

        if (categoryId == null ||
                !categoryRepository.existsById(categoryId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Nhom su co khong ton tai");
        }

        if (isBlank(description) ||
                description.length() > 1000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mo ta loi khong hop le");
        }

        if (priority == null ||
                !List.of("THAP", "TRUNG_BINH", "CAO").contains(priority)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Muc uu tien khong hop le");
        }

        ticket.setCategoryId(categoryId);
        ticket.setDescription(description.trim());
        ticket.setPriority(priority);

        Ticket updatedTicket = ticketRepository.saveAndFlush(ticket);

        return toTicketView(updatedTicket);
    }

    // Xoa phieu bao hanh
    @Transactional
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Khong tim thay phieu bao hanh"));

        ticketRepository.delete(ticket);
    }

    // Kiem tra chuoi rong
    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    // Chuyen Ticket thanh du lieu tra ve API
    private Map<String, Object> toTicketView(Ticket ticket) {

        Device device = deviceRepository.findById(ticket.getDeviceId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Khong tim thay thiet bi"));

        Customer customer = customerRepository.findById(device.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Khong tim thay khach hang"));

        IssueCategory category = categoryRepository
                .findById(ticket.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Khong tim thay nhom su co"));

        Map<String, Object> result = new java.util.LinkedHashMap<>();

        result.put("ticketId", ticket.getTicketId());
        result.put("ticketCode", ticket.getTicketCode());
        result.put("deviceName", device.getDeviceName());
        result.put("deviceType", device.getDeviceType());
        result.put("serialNumber", device.getSerialNumber());
        result.put("customerName", customer.getFullName());
        result.put("customerPhone", customer.getPhone());
        result.put("categoryId", category.getCategoryId());
        result.put("categoryName", category.getName());
        result.put("description", ticket.getDescription());
        result.put("priority", ticket.getPriority());
        result.put("status", ticket.getStatus());
        result.put("receivedAt", ticket.getReceivedAt());

        return result;
    }
}
