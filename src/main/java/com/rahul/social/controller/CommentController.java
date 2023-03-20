package com.rahul.social.controller;

import com.rahul.social.dto.CommentDto;
import com.rahul.social.dto.UserResponse;
import com.rahul.social.dto.UsersDto;
import com.rahul.social.entities.Comment;
import com.rahul.social.services.CommentService;
import com.rahul.social.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class CommentController {
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    @PostMapping("/addComment")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto){
        logger.info("Inside CommentController class addComment() method");
        CommentDto commentDto1 = commentService.addComment(commentDto);
        logger.info("Comment :: {}", commentDto1);
        return new ResponseEntity<>(commentDto, OK);
    }
}
