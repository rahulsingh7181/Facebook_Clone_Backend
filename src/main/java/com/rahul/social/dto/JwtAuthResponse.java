package com.rahul.social.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponse {
    private String token;
    private Long expiresIn;
    private String status;
    private Integer statusCode;
}
