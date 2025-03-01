package com.segaExamples.UserService.service;


import com.segaExamples.UserService.Repository.UserRepository;
import com.segaExamples.UserService.models.Authorities;
import com.segaExamples.UserService.models.Users;
import com.segaExamples.UserService.models.UserDetails;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

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
        String userAutorities = user.getUserAutorities();
        Set<Authorities> authorities = new HashSet<>();
        if(null!=userAutorities || !StringUtils.isBlank(userAutorities)){
            String [] authList =userAutorities.split(",");
            for (int i = 0; i < authList.length; i++) {
                Authorities a1 = new Authorities();
                a1.setName(authList[i]);
                authorities.add(a1);
            }
        }else{
            Authorities a1 = new Authorities();
            a1.setName("USERS");
            authorities.add(a1);
        }
        user.addAuthorities(authorities);
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
        }
        return loginUser ;
    }

    /**
     * Fetch all users
     * @return
     */
    public List<Users> fetchAllUsers() {
        return userRepository.findAll();
    }
}
