package com.ticketing.EventTicketingSystem.repository;

import com.ticketing.EventTicketingSystem.Model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends AbstractRepo<Payment, String> {
    List<Payment> findAll(); // ✅ Correct Spring Data JPA naming
}

