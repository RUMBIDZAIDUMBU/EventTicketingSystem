package com.ticketing.EventTicketingSystem.repository;
import com.ticketing.EventTicketingSystem.Model.UserRole;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleRepo extends AbstractNameRepo <UserRole, String>{
    Boolean existsByNameIgnoreCaseAndActive(String name, Boolean active);
}
