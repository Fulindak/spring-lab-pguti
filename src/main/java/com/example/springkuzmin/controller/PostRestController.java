package com.example.springkuzmin.controller;

import com.example.springkuzmin.dto.post.Blocks;
import com.example.springkuzmin.model.Post;
import com.example.springkuzmin.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostRestController {
    private final PostService postService;
    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    @RequestMapping("/posts/")
    public Post create(@RequestBody Blocks blocks){
        return postService.create(blocks);
    }
    @GetMapping
    @RequestMapping("/users/{id}/posts/")
    public List<Post> getAllByUserId(@PathVariable("id") UUID id){
        return postService.getAllByAuthorId(id);
    }
}