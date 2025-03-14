package com.ticketing.EventTicketingSystem.controller;

import com.ticketing.EventTicketingSystem.Model.ContactUs;
import com.ticketing.EventTicketingSystem.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/contact-us")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> saveContactUs(@RequestBody ContactUs contactUs) {
        Map<String, Object> response = new HashMap<>();
        try {
            ContactUs newContactUs = contactUsService.save(contactUs);

            response.put("status", "success");
            response.put("message", "Contact saved successfully");
            response.put("data", newContactUs);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            response.put("status", "error");
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

