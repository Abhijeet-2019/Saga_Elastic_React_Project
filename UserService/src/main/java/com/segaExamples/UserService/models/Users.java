package com.segaExamples.UserService.models;

import jakarta.persistence.* ;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_generator")
    @SequenceGenerator(name = "user_generator",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "last_login_time")
    private Timestamp lastLoginTime;

    @OneToOne(mappedBy = "users", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private UserDetails userDetails;

    @Transient
    private boolean isValid;

    public void addUserDetails(UserDetails userDetails){
        userDetails.setUsers(this);
        this.setUserDetails(userDetails);
    }
}
