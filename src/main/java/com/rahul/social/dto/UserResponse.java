package com.rahul.social.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String message;
    private String status;
    private Integer statusCode;
}
