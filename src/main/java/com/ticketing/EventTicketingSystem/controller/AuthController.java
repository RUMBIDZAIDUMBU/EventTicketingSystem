package com.ticketing.EventTicketingSystem.controller;
import com.ticketing.EventTicketingSystem.Model.User;
import com.ticketing.EventTicketingSystem.Security.AuthRequest;
import com.ticketing.EventTicketingSystem.Security.AuthResponse;
import com.ticketing.EventTicketingSystem.Security.AuthenticationException;

import com.ticketing.EventTicketingSystem.Security.JwtUtil;
import com.ticketing.EventTicketingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {


    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        System.out.println("username : " + authRequest.getUserName());
        System.out.println("password : " + authRequest.getPassword());

      authenticate(authRequest.getUserName(),authRequest.getPassword());

      final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());

      final String token = jwtUtil.generateToken(userDetails.getUsername());

      final User user = userService.findByUserName(authRequest.getUserName());
      user.setToken(token);

      if(user != null){
          return new ResponseEntity<>(new AuthResponse(token,user),HttpStatus.OK);
      }
        return new ResponseEntity<>(new AuthResponse(token,null),HttpStatus.OK);

    }



    private void authenticate(String username, String password) {
        System.out.println("username : " + username);
        System.out.println("password : " + password);

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }







//    @PostMapping("/userRole")
//    public ResponseEntity<Map<String, Object>> saveUserRole(@RequestBody UserRole userRole){
//        Map<String, Object> response = new HashMap<>();
//
//        try{
//
//            if (!userService.checkDuplicate(userRole,userRole)){
//                User newUser = userService.save(userRole);
//                response.put("User Saved",newUser);
//            }else{
//                response.put("Duplicate",true);
//            }
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//            response.put("message", ex.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    


}

