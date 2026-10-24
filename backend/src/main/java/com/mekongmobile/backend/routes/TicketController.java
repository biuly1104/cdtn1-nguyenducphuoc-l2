
package com.mekongmobile.backend.routes;

import com.mekongmobile.backend.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Map<String, Object>> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Map<String, Object> getTicketById(
            @PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTicket(
            @RequestBody CreateTicketRequest request) {

        Map<String, Object> ticket = ticketService.createTicket(
                request.customerId(),
                request.deviceName(),
                request.deviceType(),
                request.serialNumber(),
                request.categoryId(),
                request.description(),
                request.priority()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    public record CreateTicketRequest(
            Long customerId,
            String deviceName,
            String deviceType,
            String serialNumber,
            Long categoryId,
            String description,
            String priority
    ) {
    }
}
