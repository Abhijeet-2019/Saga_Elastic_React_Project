package com.segaExamples.UserService.Repository;

import com.segaExamples.UserService.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Users,Long> {

    List <Users>findByEmailAndPassword(String email, String password);
    Optional<Users> findByEmail(String email);

}
