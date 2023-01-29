package com.rahul.social.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthRequest {
    private String username;
    private String password;
}
