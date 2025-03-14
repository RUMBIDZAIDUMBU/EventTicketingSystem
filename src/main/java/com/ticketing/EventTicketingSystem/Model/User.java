package com.ticketing.EventTicketingSystem.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="system_users")
@TypeAlias("User")
@Data
@NoArgsConstructor
public class User extends BaseEntity {

    private String firstName;
    private String surname;
    private String password;
    private String email;
    private String userName;
    @DBRef
    private List<UserRole> userRoles;
    private String token;
}
