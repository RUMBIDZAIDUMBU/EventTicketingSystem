package com.ticketing.EventTicketingSystem.serviceimplementation;

import com.ticketing.EventTicketingSystem.Model.User;
import com.ticketing.EventTicketingSystem.repository.UserRepo;
import com.ticketing.EventTicketingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public User findByUserName(String name) {
        return userRepo.findByUserNameIgnoreCaseAndActive(name,Boolean.TRUE);
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getPageable() {
        return List.of();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null){
            user.setDateCreated(LocalDateTime.now());
            user.setPassword(encryptPassword(user.getPassword()));
           return userRepo.save(user);

        } else {
            user.setDateModified(LocalDateTime.now());
            user.setPassword(encryptPassword(user.getPassword()));
           return userRepo.save(user);
        }
    }



    @Override
    public Boolean checkDuplicate(User current, User old) {
        if (current.getId() == null) {
            return userRepo.existsByActiveAndUserNameIgnoreCase(Boolean.TRUE, current.getUserName());
        }
        old = get(current.getId());
        if (!current.getUserName().equalsIgnoreCase(old.getUserName())) {
            return userRepo.existsByActiveAndUserNameIgnoreCase(Boolean.TRUE, current.getUserName());
        } else {
            return Boolean.FALSE;
        }
    }

    private String encryptPassword(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }
}
