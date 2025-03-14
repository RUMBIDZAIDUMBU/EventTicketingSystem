package com.ticketing.EventTicketingSystem.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "roles")
@TypeAlias("UserRole")
@Data
@NoArgsConstructor
public class UserRole extends BaseName {


}

