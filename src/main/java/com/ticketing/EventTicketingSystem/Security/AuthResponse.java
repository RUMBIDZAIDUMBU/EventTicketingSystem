package com.ticketing.EventTicketingSystem.Security;

import com.ticketing.EventTicketingSystem.Model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResponse implements Serializable {

    private String token;
    private User user;

    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
