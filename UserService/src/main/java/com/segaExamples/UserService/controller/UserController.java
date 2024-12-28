package com.segaExamples.UserService.controller;


import com.segaExamples.UserService.models.Users;
import com.segaExamples.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
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


    /**
     * This method is used to validate the user
     * @param email    :
     *                  Valid email for the user.
     * @param password :
     *                 Valid password for the user.
     * @return :
     *              An instance of Users Object.
     *
     */
    @GetMapping("/validateLogin")
    public Users validateUser(@RequestParam String email, @RequestParam String password) {
        log.info("The received user name == {} &&  password is  --{}", email, password);
        return userService.validateUser(email, password);
    }
}
