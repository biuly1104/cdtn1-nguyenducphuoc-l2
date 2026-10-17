package com.mekongmobile.backend.services;

import com.mekongmobile.backend.Customer;
import com.mekongmobile.backend.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Lấy danh sách khách hàng
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Tìm khách hàng theo ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Khong tim thay khach hang"));
    }

    // Tìm khách hàng theo số điện thoại
    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Khong tim thay khach hang"));
    }

    // Thêm khách hàng
    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);

        if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "So dien thoai da ton tai");
        }

        customer.setCustomerId(null);
        return customerRepository.save(customer);
    }

    // Cập nhật khách hàng
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        validateCustomer(updatedCustomer);

        Customer existingCustomer = getCustomerById(id);

        customerRepository.findByPhone(updatedCustomer.getPhone())
                .ifPresent(customer -> {
                    if (!customer.getCustomerId().equals(id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT, "So dien thoai da ton tai");
                    }
                });

        existingCustomer.setFullName(updatedCustomer.getFullName());
        existingCustomer.setPhone(updatedCustomer.getPhone());

        return customerRepository.save(existingCustomer);
    }

    // Xóa khách hàng
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    // Kiểm tra dữ liệu đầu vào
    private void validateCustomer(Customer customer) {
        if (customer.getFullName() == null ||
                customer.getFullName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ho ten khong duoc de trong");
        }

        if (customer.getPhone() == null ||
                !customer.getPhone().matches("[0-9]{10,11}")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "So dien thoai phai co 10-11 chu so");
        }
    }
}