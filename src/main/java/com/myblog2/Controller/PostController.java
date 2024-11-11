package com.myblog2.Controller;

import com.myblog2.Payload.PostDto;
import com.myblog2.Payload.PostResponse;
import com.myblog2.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController //it will help me bind the form with the service layer and using controller layer follow mvc architecture and to get this controller layer we have to add spring web dependence jar
@RequestMapping("/api/post")
public class PostController {

    public PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //http://localhost:8080/api/post
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result) {
        //binding result checks if there is any errors in validations
        //requestbody copies the data from json object to dto

        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }

    //http://localhost:8080/api/post/1
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("post is deleted", HttpStatus.OK);
    }

    //http://localhost:8080/api/post/1
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto) {
       PostDto dto = postService.updatePost(id,postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/post
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
       PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //http://localhost:8080/api/post?pageNo=1&pageSize=5&sortBy=title&sortDir=desc (pagination)

    @GetMapping
    public PostResponse getPosts(
            @RequestParam(value = "pageNo",defaultValue ="0", required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue ="5", required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue ="id", required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue ="asc", required = false) String sortDir

    ) {
        PostResponse postResponse = postService.getPosts(pageNo,pageSize,sortBy,sortDir);
        return postResponse;
    }
     }