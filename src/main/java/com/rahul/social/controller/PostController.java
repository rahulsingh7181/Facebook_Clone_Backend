package com.rahul.social.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rahul.social.dto.PostDto;
import com.rahul.social.entities.Users;
import com.rahul.social.services.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.ParseException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final ModelMapper mapper;
    private final PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<PostDto> createPost(
            @RequestParam("postImage")MultipartFile postImage,
            @RequestParam("postData") String postData) throws IOException, ParseException {
        JsonObject jsonObject = new JsonParser().parse(postData).getAsJsonObject();
        logger.info(" ========> {}",jsonObject.get("caption"));
        logger.info(" ========> {}",Long.valueOf(jsonObject.get("userId").toString()));
        PostDto postDto = new PostDto();
        postDto.setPostImage(postImage.getBytes());
        postDto.setCaption(jsonObject.get("caption").toString());
        postDto.setUsers(Users.builder().userId(Long.valueOf(jsonObject.get("userId").toString())).build());
        logger.info("PostData {}",postDto);
        postDto = postService.createPost(postDto);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
