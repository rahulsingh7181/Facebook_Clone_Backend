package com.rahul.social.dto;

import com.rahul.social.entities.Post;
import com.rahul.social.entities.Users;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentDto {
    private Long commentId;
    private String commentMessage;
    private Date commentDate;
    private Long postId;
    private Long userId;
}
