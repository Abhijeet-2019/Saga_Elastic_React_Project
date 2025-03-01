package com.segaExamples.UserService.securityConfig;


import com.segaExamples.UserService.Repository.UserRepository;
import com.segaExamples.UserService.models.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 *
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param email the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("The email received for authentication ---{}",email);
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Cannot find the details for the user" + email));
         String authorities = users.getAuthorities().stream()
                 .map(authorities1 -> authorities1.getName()).collect(Collectors.joining(","));
        log.info("The authorities that are provided to the users  are  ---{}",authorities);
        UserDetails userDetails = User
                .withUsername(users.getUserName())
                .password(users.getPassword())
                .authorities(authorities).build();
        return userDetails;
    }
}
