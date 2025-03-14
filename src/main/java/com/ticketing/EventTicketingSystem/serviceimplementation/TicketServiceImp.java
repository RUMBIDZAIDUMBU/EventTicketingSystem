package com.ticketing.EventTicketingSystem.serviceimplementation;

import com.ticketing.EventTicketingSystem.Model.Ticket;
import com.ticketing.EventTicketingSystem.repository.TicketRepository;
import com.ticketing.EventTicketingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImp implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;



    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket get(String id) {
        return ticketRepository.findById(id).orElse(null); // Use `orElse(null)` to avoid exception if ticket not found
    }

    @Override
    public void delete(Ticket ticket) {
        ticket.setActive(Boolean.FALSE);
        ticket.setDeleted(Boolean.TRUE);
        save(ticket); // Mark ticket as deleted, instead of removing from DB
    }

    @Override
    public List<Ticket> getPageable() {
        return List.of(); // Implement pagination if needed
    }

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            ticket.setDateCreated(LocalDateTime.now());
            return ticketRepository.save(ticket);
        } else {
            ticket.setDateModified(LocalDateTime.now());
            return ticketRepository.save(ticket);
        }
    }

    @Override
    public Boolean checkDuplicate(Ticket current, Ticket old) {
        // If it's a new ticket (current has no ID), check for duplicates
        if (current.getId() == null) {
            return ticketRepository.existsByTitleIgnoreCaseAndActive(current.getTitle(), Boolean.TRUE);
        }

        // If updating an existing ticket, fetch the old ticket and compare
        old = get(current.getId());
        if (old == null || current.getTitle() == null) {
            return false; // Avoid NPE or invalid checks
        }

        // If the title has changed, check for duplicates
        if (!current.getTitle().equalsIgnoreCase(old.getTitle())) {
            return ticketRepository.existsByTitleIgnoreCaseAndActive(current.getTitle(), Boolean.TRUE);
        }
        return false;
    }


    @Override
    public void update(String id, Ticket ticket) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket != null) {
            // Check and update each field, ensuring no null values are incorrectly set
            if (ticket.getTitle() != null) {
                existingTicket.setTitle(ticket.getTitle());
            }
            if (ticket.getDescription() != null) {
                existingTicket.setDescription(ticket.getDescription());
            }
            if (ticket.getTitle() != null) {
                existingTicket.setTitle(ticket.getTitle());
            }
            if (ticket.getVenue() != null) {
                existingTicket.setVenue(ticket.getVenue());
            }
            if (ticket.getArtist() != null) {
                existingTicket.setArtist(ticket.getArtist());
            }
            if (ticket.getLocation() != null) {
                existingTicket.setLocation(ticket.getLocation());
            }
            if (ticket.getTicketNumber() != null) {
                existingTicket.setTicketNumber(ticket.getTicketNumber());
            }
            if (ticket.getBanner() != null) {
                existingTicket.setBanner(ticket.getBanner());
            }
            // Update the modified date
            existingTicket.setDateModified(LocalDateTime.now());

            // Save the updated ticket
            ticketRepository.save(existingTicket);
        } else {
            throw new RuntimeException("Ticket not found");
        }
    }


    @Override
    public List<Ticket> getAllActiveTickets() {
        return ticketRepository.findByActive(Boolean.TRUE); // Fetch active tickets from DB
    }

    @Override
    public List<Ticket> getAllInactiveTickets() {
        return ticketRepository.findByActive(Boolean.FALSE); // Fetch inactive tickets (soft-deleted)
    }
}
