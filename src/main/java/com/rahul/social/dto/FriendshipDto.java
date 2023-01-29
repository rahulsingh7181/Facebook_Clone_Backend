package com.rahul.social.dto;

import com.rahul.social.enums.UserStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FriendshipDto {
    private Long senderId;
    private Long receiverId;
    private UserStatus status;
    private Date requestDate;
    private Date acceptDate;
}
