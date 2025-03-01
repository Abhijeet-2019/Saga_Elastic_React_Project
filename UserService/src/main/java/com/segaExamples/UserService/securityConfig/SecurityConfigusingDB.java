package com.segaExamples.UserService.securityConfig;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is the default security class that uses DB for spring security.
 */

//@Configuration  // This is disabled to allow othet authe process
//@EnableWebSecurity
public class SecurityConfigusingDB {


    @Autowired
    CustomUserDetailsService customUserDetailsService; // This class is used to load user details


    /**
     * This method of Spring security uses Database authentication to authenticate users.
     * We are using Http basic authentication where user id and passwords are send in Headers of the application.
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain defaultServletChain(HttpSecurity http) throws Exception {
        http.csrf(crsf -> crsf.disable())
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                        config.setAllowedMethods(Arrays.asList("GET", "POST"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.addAllowedHeader("*");
                        config.setAllowCredentials(true);
                        config.setMaxAge(3600L);
                        return config;
                    }
                })).authorizeHttpRequests(auth -> auth
                        .requestMatchers("user/check", "/user/SignUp").permitAll()
                        .anyRequest().authenticated())


                .userDetailsService(customUserDetailsService)  // This is used to tell spring security to use DB for authentication.
                .httpBasic(Customizer.withDefaults());       // This line of code tells spring security to use HTTP basic authentication ..
        return http.build();
    }


    /**
     * This method is used wehen we need authentication in memory..
     *
     * @return
     */
//    @Bean
//    UserDetailsService createUserDetails(){
//        UserDetails userDetails1 = User.withUsername("Abhijeet").password("aaa").authorities("User").build();
//        UserDetails userDetails2 = User.withUsername("admin").password("admin").authorities("ADMIN").build();
//        return  new InMemoryUserDetailsManager(userDetails2,userDetails1);
//    }
    @Bean
    PasswordEncoder createPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


//                .formLogin(Customizer.withDefaults())   ; //  This is the default implementation
//        http.formLogin(flp -> flp.disable());                       // This is used when we need to disable form authentication
//                .httpBasic(Customizer.withDefaults());
}
