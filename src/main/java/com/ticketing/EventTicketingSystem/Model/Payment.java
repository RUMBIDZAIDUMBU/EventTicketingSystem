package com.ticketing.EventTicketingSystem.Model;

import com.ticketing.EventTicketingSystem.common.PaymentPlatform;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@TypeAlias("Transaction")
@Document(collection="transactions") // MongoDB collection name
public class Payment extends BaseEntity{

    private PaymentPlatform paymentPlatform;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private int quantity;
    private double totalAmount;
    private String paymentMethod;
    private String paynowChannel;
}
