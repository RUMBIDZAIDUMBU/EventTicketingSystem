package com.ticketing.EventTicketingSystem.service;

import com.ticketing.EventTicketingSystem.Model.Payment;
import com.ticketing.EventTicketingSystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    List<Payment> getAllPayments();
    Payment processPayment(Payment payment);
}
