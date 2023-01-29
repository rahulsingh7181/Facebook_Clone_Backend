package com.rahul.social.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private String gender;
    private Date dateOfBirth;
    private String password;
    private Date joiningDate;
    private String profilePic;
    private String role;
}
