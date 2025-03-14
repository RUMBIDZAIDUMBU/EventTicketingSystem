package com.ticketing.EventTicketingSystem.repository;

import com.ticketing.EventTicketingSystem.Model.ContactUs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactUsRepo extends AbstractRepo <ContactUs, String>{
    List<ContactUs> findByEmail(String email);

}
