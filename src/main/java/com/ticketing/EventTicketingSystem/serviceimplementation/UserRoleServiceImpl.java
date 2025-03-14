package com.ticketing.EventTicketingSystem.serviceimplementation;

import com.ticketing.EventTicketingSystem.Model.UserRole;
import com.ticketing.EventTicketingSystem.repository.UserRoleRepo;
import com.ticketing.EventTicketingSystem.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public List<UserRole> getAll() {
        log.info("Fetching all user roles");
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole get(String id) {
        log.info("Fetching user role with ID: {}", id);
        return userRoleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found for ID: " + id));
    }

    @Override
    public UserRole save(UserRole userRole) {
        if (userRole.getId() == null) {
            log.info("Creating new user role");
            userRole.setDateCreated(LocalDateTime.now());

        } else {
            log.info("Updating user role with ID: {}", userRole.getId());
            userRole.setDateModified(LocalDateTime.now());

        }
        return userRoleRepo.save(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        log.info("Deleting user role with ID: {}", userRole.getId());
        userRoleRepo.delete(userRole);
    }

    @Override
    public List<UserRole> getPageable() {
        return List.of();
    }

    @Override
    public Boolean checkDuplicate(UserRole current, UserRole old) {
        log.info("Checking for duplicate user roles");
        if (current.getId() == null) {
            return userRoleRepo.existsByNameIgnoreCaseAndActive(current.getName(), Boolean.TRUE);
        }
        old = get(current.getId());
        if (!current.getName().equalsIgnoreCase(old.getName())) {
            return userRoleRepo.existsByNameIgnoreCaseAndActive(current.getName(), Boolean.TRUE);
        } else {
            return Boolean.FALSE;
        }
    }
}
