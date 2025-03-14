package com.ticketing.EventTicketingSystem.Security;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthRequest implements Serializable {
    private String userName;
    private String password;

}
