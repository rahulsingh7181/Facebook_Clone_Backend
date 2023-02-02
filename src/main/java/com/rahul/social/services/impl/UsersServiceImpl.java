package com.rahul.social.services.impl;

import com.rahul.social.constants.AppConstants;
import com.rahul.social.dto.UserResponse;
import com.rahul.social.dto.UsersDto;
import com.rahul.social.entities.Users;
import com.rahul.social.repository.UsersRepository;
import com.rahul.social.services.UsersService;
import com.rahul.social.utility.GeneralUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    // Signup User start
    @Override
    public UserResponse signUpUser(UsersDto usersDto) {
        logger.info("Inside UsersServiceImpl class signUpUser() method");
        UserResponse userResponse = new UserResponse();
        String profileImagePath = GeneralUtility.concat(AppConstants.ROOT_FILE_PATH,"/",
                AppConstants.PROFILE_FOLDER_PATH,"/default.png");
        logger.info(profileImagePath);
        Users emailAddress = usersRepository.findByEmailAddress(usersDto.getEmailAddress());
        List<Users> mobileNumber = usersRepository.findByMobileNumber(usersDto.getMobileNumber());
        if(emailAddress != null){
            logger.info("Email Address already present. Please enter different.");
            userResponse.setMessage("Email Address already exists.");
            userResponse.setStatus("EMAIL_ALREADY_EXISTS");
            userResponse.setStatusCode(403);
        }else if(mobileNumber != null && mobileNumber.size() > 0){
            logger.info("Mobile Number already present. Please enter different.");
            userResponse.setMessage("Mobile number already exists.");
            userResponse.setStatus("MOBILE_NUMBER_ALREADY_EXISTS");
            userResponse.setStatusCode(403);
        }else{
            Users users
                    = Users.builder()
                    .firstName(usersDto.getFirstName())
                    .lastName(usersDto.getLastName())
                    .emailAddress(usersDto.getEmailAddress())
                    .mobileNumber(usersDto.getMobileNumber())
                    .gender(usersDto.getGender())
                    .dateOfBirth(usersDto.getDateOfBirth())
                    .password(passwordEncoder.encode(usersDto.getPassword()))
                    .joiningDate(new Date())
                    .profilePic(null)
                    .role("USER")
                    .build();
            usersRepository.save(users);
            logger.info("User signed up successfully...");
            userResponse.setMessage("User signed up successfully.");
            userResponse.setStatus("USER_CREATED");
            userResponse.setStatusCode(201);
        }
        return userResponse;
    }

    // get user by emailAddress
    @Override
    public UsersDto getUser(String emailAddress) {
        return modelMapper.map(usersRepository.findByEmailAddress(emailAddress), UsersDto.class);
    }

    // search users by search string
    @Override
    public List<UsersDto> searchUsers(String searchString) {
        List<Users> users = usersRepository.searchUsers(searchString);
        return users.stream().map((users1)-> this.modelMapper.map(users1, UsersDto.class)).collect(Collectors.toList());
    }
}
