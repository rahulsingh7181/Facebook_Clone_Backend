package com.rahul.social.services;

import com.rahul.social.dto.PostDto;

import java.io.IOException;

public interface PostService {

    PostDto createPost(PostDto postDto) throws IOException;
}
