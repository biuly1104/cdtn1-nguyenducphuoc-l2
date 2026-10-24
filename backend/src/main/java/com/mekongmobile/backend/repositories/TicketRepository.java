
package com.mekongmobile.backend.repositories;

import com.mekongmobile.backend.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository
        extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByOrderByReceivedAtDesc();
}
