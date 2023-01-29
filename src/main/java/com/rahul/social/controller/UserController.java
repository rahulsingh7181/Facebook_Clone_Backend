package com.rahul.social.controller;

import com.rahul.social.dto.UserResponse;
import com.rahul.social.dto.UsersDto;
import com.rahul.social.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    // user signup
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUpUser(@Valid @RequestBody UsersDto usersDto){
        logger.info("Inside UsersController class signUpUser() method");
        UserResponse userResponse = usersService.signUpUser(usersDto);
        logger.info("-------->>"+userResponse.getMessage());
        return new ResponseEntity<>(userResponse, CREATED);
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UsersDto>> searchUsers(@RequestParam(value = "searchString", required = true) String searchString){
        if(searchString == null || searchString.equals("")){
            return null;
        }
        return ResponseEntity.ok(usersService.searchUsers(searchString));
    }
}
