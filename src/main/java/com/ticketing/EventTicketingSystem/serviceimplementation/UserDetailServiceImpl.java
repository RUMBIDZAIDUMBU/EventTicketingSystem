package com.ticketing.EventTicketingSystem.serviceimplementation;


import com.ticketing.EventTicketingSystem.Model.User;
import com.ticketing.EventTicketingSystem.Model.UserRole;
import com.ticketing.EventTicketingSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    log.debug("Attempting to load user: {}", username);

    // Fetch user from the database
    User user = userService.findByUserName(username);

    if (user == null) {
        log.info("User {} not found", username);
        throw new UsernameNotFoundException("User " + username + " not found");
    }

    log.debug("User found: {}", user.getUserName());

    // Extract password
    String password = user.getPassword();
    if (password == null) {
        throw new IllegalStateException("Password is missing for user: " + username);
    }

    log.debug("User password retrieved");



    // Extract roles
    Set<UserRole> roles = new HashSet<>(user.getUserRoles());
    if (roles == null || roles.isEmpty()) {
        throw new IllegalStateException("Roles are missing for user: " + username);
    }

    log.debug("User roles: {}", roles);

    // Convert roles to authorities
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (UserRole role : roles) {
        String roleName = role.getName(); // Use the proper getter
        log.debug("Processing role: {}", roleName);
        authorities.add(new SimpleGrantedAuthority(roleName));
    }

    // Create UserDetails object
    UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, password, authorities);
    log.info("Successfully loaded user details for username: {}", username);

    return userDetails;
}

}
