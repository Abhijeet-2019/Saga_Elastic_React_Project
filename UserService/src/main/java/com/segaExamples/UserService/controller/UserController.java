package com.segaExamples.UserService.controller;


import com.segaExamples.UserService.models.Users;
import com.segaExamples.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3030")
@RequestMapping("user")
public class UserController {


    @Autowired
    UserService userService;

    /**
     * Method to add user to the application.
     *
     * @param user :
     */
    @PostMapping("/SignUp")
    public String signUpUser(@RequestBody Users user) {
        log.info("Received Sign Up request for user {}", user);
        userService.saveUser(user);
        return "Users added successfully";
    }


    @PostMapping("/validateLogin")
    public ResponseEntity validateUser(@RequestBody Map<String, String> requestBody) {
        log.info("Checking the details  _+"+requestBody);
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        Users userDetails  = userService.validateUser(email, password);
        if(userDetails == null){
            return (ResponseEntity) ResponseEntity.notFound();
        }
        return ResponseEntity.ok().body(userDetails);
    }

    /**
     * This method is used to fetch all user in DB.
     * @return
     */

    @RequestMapping("/fetchAllData")
    public ResponseEntity fetchAllUsers(){
       List<Users> fetchAllUsers= userService.fetchAllUsers();
       if(fetchAllUsers.isEmpty()){
           return ResponseEntity.ok().body("No Data found");
       }
       return ResponseEntity.ok().body(fetchAllUsers);
   }
}
