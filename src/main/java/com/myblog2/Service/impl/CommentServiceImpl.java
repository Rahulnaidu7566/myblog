package com.myblog2.Service.impl;

import com.myblog2.Entity.Comment;
import com.myblog2.Entity.Post;
import com.myblog2.Exception.ResourceNotFound;
import com.myblog2.Payload.CommentDto;
import com.myblog2.Repository.CommentRepository;
import com.myblog2.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFound("Post not found with id" +postId));

        // set post to comment entity
        comment.setPost(post);
        // comment entity to DB
        Comment savedComment =commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFound("Post not found with id" +postId));
        List<Comment> comment = commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = comment.stream().map(Comment -> mapToDto(Comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto getCommentsById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFound("Post not found with id" +postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFound("comment not found with id" +commentId));
        CommentDto commentDto = mapToDto(comment);
        return commentDto;
    }

    @Override
    public List<CommentDto> getAllCommentsById() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFound("Post not found with id" +postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFound("comment not found with id" +commentId));
        commentRepository.deleteById(commentId);
    }


    private CommentDto mapToDto(Comment savedComment){
        CommentDto dto = mapper.map(savedComment, CommentDto.class);
        return dto;
    }
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;

    }
}
