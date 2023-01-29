package com.rahul.social.services.impl;

import com.rahul.social.dto.PostDto;
import com.rahul.social.entities.Post;
import com.rahul.social.repository.PostRepository;
import com.rahul.social.services.PostService;
import com.rahul.social.utility.FileUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final FileUtility fileUtility;

    @Override
    public PostDto createPost(PostDto postDto) throws IOException {
        logger.info("Inside PostService class createPost() method");
        Post post = Post.builder()
                .caption(postDto.getCaption())
                .postImage(fileUtility.compressBytes(postDto.getPostImage()))
                .postDate(new Date())
                .isPostActive(true)
                .users(postDto.getUsers())
                .build();
        postRepository.save(post);
        return postDto;
    }

}
