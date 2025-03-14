package com.ticketing.EventTicketingSystem.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection="tickets")
@TypeAlias("Ticket")
@Data
@NoArgsConstructor


public class Ticket extends BaseEntity {


    private Double earlyBirdPrice;
    private Double vipPrice;
    private Double generalPrice;
    private String venue;
    private String title;
    private String description;
    private String date;
    private String artist;
    private String location;
    private String ticketNumber;
    private String banner; // Store Base64 image data


}
