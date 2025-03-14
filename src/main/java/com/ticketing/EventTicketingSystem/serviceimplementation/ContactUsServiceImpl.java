package com.ticketing.EventTicketingSystem.serviceimplementation;

import com.ticketing.EventTicketingSystem.Model.ContactUs;
import com.ticketing.EventTicketingSystem.repository.ContactUsRepo;
import com.ticketing.EventTicketingSystem.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepo contactUsRepo;

    @Autowired
    public ContactUsServiceImpl(ContactUsRepo contactUsRepo) {
        this.contactUsRepo = contactUsRepo;
    }


    @Override
    public List<ContactUs> getAll() {
        return contactUsRepo.findAll();
    }

    @Override
    public ContactUs get(String id) {
        return contactUsRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(ContactUs contactUs) {
        contactUsRepo.delete(contactUs);
    }

    @Override
    public List<ContactUs> getPageable() {
        return contactUsRepo.findAll();
    }

    @Override
    public ContactUs save(ContactUs contactUs) {
        return contactUsRepo.save(contactUs);
    }

    @Override
    public Boolean checkDuplicate(ContactUs current, ContactUs old) {
        if (current.getEmail().equals(old.getEmail()) && current.getId().equals(old.getId())) {
            return true;
        }
        return false;
    }
}
