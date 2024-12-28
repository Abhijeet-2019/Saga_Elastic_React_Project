package com.segaExamples.UserService.service;


import com.segaExamples.UserService.Repository.UserRepository;
import com.segaExamples.UserService.models.Users;
import com.segaExamples.UserService.models.UserDetails;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param paramUser :
     */
    @Transactional
    public  void saveUser(Users paramUser){
        log.info("The current user details to save{}",paramUser.getUserDetails());
        Users user = new Users();
        user = paramUser;
        UserDetails userDetails = new UserDetails();
        userDetails.setAddress(paramUser.getUserDetails().getAddress());
        userDetails.setCity(paramUser.getUserDetails().getCity());
        userDetails.setCellNo(paramUser.getUserDetails().getCellNo());
        userDetails.setPinCode(paramUser.getUserDetails().getPinCode());
        userDetails.setAmountInWallet(paramUser.getUserDetails().getAmountInWallet());
        user.addUserDetails(userDetails);
        try{
            userRepository.save(paramUser);
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param email :
     *
     * @param password :
     */
    public Users validateUser(String email, String password) {
        List <Users> userList =  userRepository.findByEmailAndPassword(email , password);
        Users loginUser = null;
        if (!userList.isEmpty()){
            loginUser = (Users) userList.get(0);
            loginUser.setValid(true);
        }
        return loginUser ;
    }
}
