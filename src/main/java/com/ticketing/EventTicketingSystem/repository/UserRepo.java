package com.ticketing.EventTicketingSystem.repository;

import com.ticketing.EventTicketingSystem.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends AbstractRepo<User,String>{

    public Boolean existsByActiveAndUserNameIgnoreCase(Boolean active, String userName);
    User findByUserNameIgnoreCaseAndActive(String userName,Boolean active);

}
