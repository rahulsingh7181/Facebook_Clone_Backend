package com.rahul.social.services.impl;

import com.rahul.social.dto.CommentDto;
import com.rahul.social.entities.Comment;
import com.rahul.social.entities.Post;
import com.rahul.social.entities.Users;
import com.rahul.social.repository.CommentRepository;
import com.rahul.social.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

    @Override
    public CommentDto addComment(CommentDto commentDto) {
        Post post = new Post();
        post.setPostId(commentDto.getPostId());
        Users users = new Users();
        users.setUserId(commentDto.getUserId());
        Comment comment = new Comment();
        comment.setCommentMessage(commentDto.getCommentMessage());
        comment.setCommentDate(new Date());
        comment.setPost(post);
        comment.setUser(users);
        Comment comment1 = commentRepository.save(comment);
        return CommentDto.builder()
                .commentId(comment1.getCommentId())
                .commentMessage(comment1.getCommentMessage())
                .commentDate(comment1.getCommentDate())
                .postId(comment1.getPost().getPostId())
                .userId(comment1.getUser().getUserId()).build();
    }
}
