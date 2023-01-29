package com.rahul.social.dto;

import com.rahul.social.entities.Users;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Long postId;

    private String caption;

    private byte[] postImage;

    private Date postDate;

    private Boolean isPostActive;

    private Users users;
}
