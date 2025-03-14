package com.ticketing.EventTicketingSystem.service;

import com.ticketing.EventTicketingSystem.Model.User;

public interface UserService extends GenericService<User>{

    User findByUserName(String name);

}
