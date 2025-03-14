package com.ticketing.EventTicketingSystem.controller;

import com.ticketing.EventTicketingSystem.Model.User;
import com.ticketing.EventTicketingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")


public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();

        try{

            if (!userService.checkDuplicate(user,user)){
                User newUser = userService.save(user);
                response.put("User Saved",newUser);
            }else{
                response.put("Duplicate",true);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            response.put("message", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
