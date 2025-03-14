package com.ticketing.EventTicketingSystem.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="contactUs")
@TypeAlias("ContactUs")
@Data
@NoArgsConstructor
public class ContactUs extends BaseEntity{

    private String name;
    private String email;
    private String message;

}
