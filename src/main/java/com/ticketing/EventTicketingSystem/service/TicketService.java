package com.ticketing.EventTicketingSystem.service;
import com.ticketing.EventTicketingSystem.Model.Ticket;

import java.util.List;

public interface TicketService extends GenericService <Ticket>{
    void update(String id, Ticket ticket);


    List<Ticket> getAllActiveTickets();
    List<Ticket> getAllInactiveTickets();
}
