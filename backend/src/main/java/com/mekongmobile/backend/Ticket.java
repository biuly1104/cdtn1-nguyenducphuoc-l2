
package com.mekongmobile.backend;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "ticket_code", nullable = false, unique = true)
    private String ticketCode;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "priority", nullable = false)
    private String priority;

    @Column(name = "status", nullable = false)
    private String status = "MOI_TIEP_NHAN";

    @Column(name = "received_at", insertable = false, updatable = false)
    private LocalDateTime receivedAt;

    public Long getTicketId() {
        return ticketId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }
}
