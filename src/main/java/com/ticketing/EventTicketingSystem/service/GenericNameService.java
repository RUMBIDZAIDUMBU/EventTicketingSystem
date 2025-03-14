package com.ticketing.EventTicketingSystem.service;

import java.io.Serializable;

public interface GenericNameService <T extends Serializable> extends GenericService<T>{
    public T getByName(String name);
}
