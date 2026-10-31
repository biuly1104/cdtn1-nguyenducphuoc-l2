
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

    // GET - Danh sach phieu bao hanh
    @GetMapping
    public List<Map<String, Object>> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // GET - Chi tiet phieu bao hanh
    @GetMapping("/{id}")
    public Map<String, Object> getTicketById(
            @PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    // POST - Tao phieu bao hanh
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

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ticket);
    }

    // PUT - Cap nhat phieu bao hanh
    @PutMapping("/{id}")
    public Map<String, Object> updateTicket(
            @PathVariable Long id,
            @RequestBody UpdateTicketRequest request) {

        return ticketService.updateTicket(
                id,
                request.categoryId(),
                request.description(),
                request.priority()
        );
    }

    // DELETE - Xoa phieu bao hanh
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable Long id) {

        ticketService.deleteTicket(id);

        return ResponseEntity.noContent().build();
    }

    // Du lieu tao phieu
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

    // Du lieu cap nhat phieu
    public record UpdateTicketRequest(
            Long categoryId,
            String description,
            String priority
    ) {
    }
}
