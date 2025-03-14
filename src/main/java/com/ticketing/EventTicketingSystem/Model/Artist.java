package com.ticketing.EventTicketingSystem.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="artists")
@TypeAlias("Artist")
@Data
@NoArgsConstructor
public class Artist extends BaseName{

    private String date;
}
