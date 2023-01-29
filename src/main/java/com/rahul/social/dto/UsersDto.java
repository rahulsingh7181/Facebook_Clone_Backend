package com.rahul.social.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDto {
    private Long userId;
    @NotEmpty(message = "First name is mandatory.")
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstName;
    @NotEmpty(message = "Last name is mandatory.")
    private String lastName;
    @NotEmpty(message = "Email address is mandatory.")
    @Email(message = "Please enter a valid email.")
    private String emailAddress;
    @NotEmpty(message = "Mobile number is mandatory.")
    @Size(min = 10, max = 10, message = "Mobile number is must be 10 digit.")
    private String mobileNumber;
    @NotEmpty(message = "Please select gender")
    private String gender;
    @NotNull(message = "Date of birth is mandatory.")
    private Date dateOfBirth;
    @NotEmpty(message = "Password is mandatory.")
    @Size(min = 6, message = "Password should have at least 6 characters.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Date joiningDate;
    private String profilePic;
    private String role;

}
