package com.rahul.social.services;

import com.rahul.social.dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto) throws IOException;

    List<PostDto> getAllPosts(Long userId);

}
