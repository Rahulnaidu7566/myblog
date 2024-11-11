package com.myblog2.Service.impl;

import com.myblog2.Payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentsById(long postId, long commentId);

    List<CommentDto> getAllCommentsById();

    void deleteCommentById(Long postId, Long commentId);
}
