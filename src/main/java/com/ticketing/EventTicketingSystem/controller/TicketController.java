package com.ticketing.EventTicketingSystem.controller;

import com.ticketing.EventTicketingSystem.Model.Artist;
import com.ticketing.EventTicketingSystem.Model.Ticket;
import com.ticketing.EventTicketingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> saveTicket(@RequestBody Ticket ticket){
        Map<String, Object> response = new HashMap<>();

        try {
            if (!ticketService.checkDuplicate(ticket, ticket)){
                Ticket newTicket = ticketService.save(ticket);
                response.put("Ticket Saved", newTicket);
            } else {
                response.put("Duplicate", true);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteTicket(@PathVariable("id") String id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Ticket ticket = ticketService.get(id);
            if (ticket == null) {
                response.put("message", "Ticket not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            ticketService.delete(ticket);
            response.put("message", "Ticket deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            response.put("message", "System error occurred while deleting ticket.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable String id, @RequestBody Ticket ticket) {
        ticketService.update(id, ticket);
        return new ResponseEntity<>("Ticket updated successfully", HttpStatus.OK);
    }

    @GetMapping("/active-tickets")
    public List<Ticket> getActiveTickets() {
        return ticketService.getAllActiveTickets();
    }

    @GetMapping("/inactive-tickets")
    public List<Ticket> getInactiveTickets() {
        return ticketService.getAllInactiveTickets();
    }

    @GetMapping("/banner/active-tickets")
    public List<Ticket> getBannerActiveTickets() {
        return ticketService.getAllActiveTickets();
    }


}
