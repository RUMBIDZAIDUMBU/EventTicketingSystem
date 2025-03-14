package com.ticketing.EventTicketingSystem.controller;
import com.ticketing.EventTicketingSystem.Model.UserRole;
import com.ticketing.EventTicketingSystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/role")

public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;


    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveUserRole(@RequestBody UserRole userRole) {
        Map<String, Object> response = new HashMap<>();


        try{

            if (!userRoleService.checkDuplicate(userRole,userRole)){
                UserRole newUserRole = userRoleService.save(userRole);
                response.put("Role Saved",newUserRole);
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

