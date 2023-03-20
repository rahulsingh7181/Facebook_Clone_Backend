package com.rahul.social.controller;

import com.rahul.social.dto.JwtAuthRequest;
import com.rahul.social.dto.JwtAuthResponse;
import com.rahul.social.dto.UsersDto;
import com.rahul.social.security.CustomUserDetailsService;
import com.rahul.social.security.JwtTokenHelper;
import com.rahul.social.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final JwtTokenHelper jwtTokenHelper;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> generateToken(@RequestBody JwtAuthRequest jwtRequest) throws Exception{
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        if(userDetails != null){
            jwtAuthResponse.setToken(jwtTokenHelper.generateToken(userDetails).get("token").toString());
            jwtAuthResponse.setExpiresIn((Long) jwtTokenHelper.generateToken(userDetails).get("expiresIn"));
            jwtAuthResponse.setStatus("SUCCESS");
            jwtAuthResponse.setStatusCode(HttpStatus.OK.value());
        }
        return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<UsersDto> getCurrentUser(Principal principal){
        UsersDto usersDto = usersService.getUser(principal.getName());
        return new ResponseEntity<UsersDto>(usersDto, HttpStatus.OK);
    }
}
