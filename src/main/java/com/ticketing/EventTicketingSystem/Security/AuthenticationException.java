package com.ticketing.EventTicketingSystem.Security;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);

    }
}