package com.ticketing.EventTicketingSystem.serviceimplementation;

import com.ticketing.EventTicketingSystem.Model.Payment;
import com.ticketing.EventTicketingSystem.repository.PaymentRepository;
import com.ticketing.EventTicketingSystem.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    private final String PAYNOW_API_URL = "https://api.paynow.com"; // Replace with actual Paynow API URL

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment processPayment(Payment payment) {
        // Save the payment record to MongoDB
        Payment savedPayment = paymentRepository.save(payment);

        // If payment method is Paynow, initiate Paynow payment process
        if ("paynow".equals(payment.getPaymentMethod())) {
            initiatePaynowPayment(savedPayment);
        }

        return savedPayment;
    }

    private void initiatePaynowPayment(Payment payment) {
        // Prepare the data required for Paynow (adjust according to Paynow API documentation)
        String paynowChannel = payment.getPaynowChannel();
        double totalAmount = payment.getTotalAmount();

        // Paynow request details (Example, adjust according to Paynow's API documentation)
        String paynowApiUrl = PAYNOW_API_URL + "/payments";  // Replace with actual Paynow endpoint

        // Create Paynow payment request body
        // For instance:
        String requestBody = "{ \"amount\": " + totalAmount + ", \"channel\": \"" + paynowChannel + "\" }";

        // Use RestTemplate to send request to Paynow API
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(paynowApiUrl, requestBody, String.class);

        // Handle the response from Paynow (e.g., redirect URL for the user)
        System.out.println("Paynow response: " + response);

        // Implement logic to handle Paynow response (e.g., storing payment URL or redirecting)
    }
}
