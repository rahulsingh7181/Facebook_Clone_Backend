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
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class PostController {
    private final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final ModelMapper mapper;
    private final PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<PostDto> createPost(
            @RequestParam("postImage") MultipartFile postImage,
            @RequestParam("postData") String postData) throws IOException, ParseException {
        JsonObject jsonObject = new JsonParser().parse(postData).getAsJsonObject();
        logger.info(" ========> {}",jsonObject.get("caption"));
        logger.info(" ========> {}",Long.valueOf(jsonObject.get("userId").toString()));
        PostDto postDto = new PostDto();
        byte[] encoded = Base64.getEncoder().encode(postImage.getBytes());
        postDto.setPostImage(new String(encoded));
        if(jsonObject.get("caption").toString().equals("null")){
            postDto.setCaption(null);
        }else{
            postDto.setCaption(jsonObject.get("caption").toString());
        }

        postDto.setUsers(Users.builder().userId(Long.valueOf(jsonObject.get("userId").toString())).build());
        logger.info("PostData {}",postDto);
        postDto = postService.createPost(postDto);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/getAllPosts/{userId}")
    public ResponseEntity<List<PostDto>> getAllPosts(@PathVariable("userId") Long userId){
        logger.info("inside getAllPosts() method in PostController class userId : {}", userId);
        return new ResponseEntity<List<PostDto>>(postService.getAllPosts(userId), HttpStatus.OK);
    }
}
