
package com.mekongmobile.backend.services;

import com.mekongmobile.backend.repositories.CustomerRepository;
import com.mekongmobile.backend.repositories.DeviceRepository;
import com.mekongmobile.backend.repositories.IssueCategoryRepository;
import com.mekongmobile.backend.repositories.TicketRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    private TicketRepository ticketRepository;
    private DeviceRepository deviceRepository;
    private CustomerRepository customerRepository;
    private IssueCategoryRepository categoryRepository;
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketRepository = mock(TicketRepository.class);
        deviceRepository = mock(DeviceRepository.class);
        customerRepository = mock(CustomerRepository.class);
        categoryRepository = mock(IssueCategoryRepository.class);

        ticketService = new TicketService(
                ticketRepository,
                deviceRepository,
                customerRepository,
                categoryRepository
        );
    }

    private void createSampleTicket(
            String deviceName,
            String description,
            String priority) {

        ticketService.createTicket(
                1L,
                deviceName,
                "Dien thoai",
                "SN123456789",
                1L,
                description,
                priority
        );
    }

    @Test
    void testCustomerNotFound() {
        when(customerRepository.existsById(1L))
                .thenReturn(false);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class,
                        () -> createSampleTicket(
                                "iPhone 13",
                                "Soc man",
                                "TRUNG_BINH"));

        assertEquals(HttpStatus.NOT_FOUND,
                exception.getStatusCode());
    }

    @Test
    void testCategoryNotFound() {
        when(customerRepository.existsById(1L))
                .thenReturn(true);

        when(categoryRepository.existsById(1L))
                .thenReturn(false);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class,
                        () -> createSampleTicket(
                                "iPhone 13",
                                "Soc man",
                                "TRUNG_BINH"));

        assertEquals(HttpStatus.NOT_FOUND,
                exception.getStatusCode());
    }

    @Test
    void testEmptyDeviceName() {
        mockValidReferences();

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class,
                        () -> createSampleTicket(
                                "",
                                "Soc man",
                                "TRUNG_BINH"));

        assertEquals(HttpStatus.BAD_REQUEST,
                exception.getStatusCode());
    }

    @Test
    void testInvalidPriority() {
        mockValidReferences();

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class,
                        () -> createSampleTicket(
                                "iPhone 13",
                                "Soc man",
                                "KHONG_HOP_LE"));

        assertEquals(HttpStatus.BAD_REQUEST,
                exception.getStatusCode());
    }

    @Test
    void testDescriptionTooLong() {
        mockValidReferences();

        String longDescription = "A".repeat(1001);

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class,
                        () -> createSampleTicket(
                                "iPhone 13",
                                longDescription,
                                "TRUNG_BINH"));

        assertEquals(HttpStatus.BAD_REQUEST,
                exception.getStatusCode());
    }

    @Test
    void testTicketNotFound() {
        when(ticketRepository.findById(999L))
                .thenReturn(Optional.empty());

        ResponseStatusException exception =
                assertThrows(ResponseStatusException.class,
                        () -> ticketService.getTicketById(999L));

        assertEquals(HttpStatus.NOT_FOUND,
                exception.getStatusCode());
    }

    private void mockValidReferences() {
        when(customerRepository.existsById(1L))
                .thenReturn(true);

        when(categoryRepository.existsById(1L))
                .thenReturn(true);
    }
}
