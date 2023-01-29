package com.rahul.social.services;

import com.rahul.social.dto.UserResponse;
import com.rahul.social.dto.UsersDto;

import java.util.List;

public interface UsersService {
    UserResponse signUpUser(UsersDto usersDto);

    UsersDto getUser(String emailAddress);

    List<UsersDto> searchUsers(String searchString);
}
