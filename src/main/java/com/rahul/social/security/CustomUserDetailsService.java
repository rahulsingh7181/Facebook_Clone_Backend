package com.rahul.social.security;

import com.rahul.social.entities.Users;
import com.rahul.social.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Users user = this.usersRepository.findByEmailAddress(emailAddress);
        if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + emailAddress);
		}
		return new CustomUserDetails(user);
    }
}
