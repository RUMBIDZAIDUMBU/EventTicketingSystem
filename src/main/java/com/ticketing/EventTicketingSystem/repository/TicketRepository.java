package com.ticketing.EventTicketingSystem.repository;

import com.ticketing.EventTicketingSystem.Model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends AbstractRepo<Ticket, String> {

    List<Ticket> findByActive(Boolean active);

    Boolean existsByTitleIgnoreCaseAndActive(String title, Boolean active);

}
