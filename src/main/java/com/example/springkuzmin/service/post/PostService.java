package com.example.springkuzmin.service.post;

import com.example.springkuzmin.dto.post.Blocks;
import com.example.springkuzmin.model.Post;
import com.example.springkuzmin.repository.PostRepository;
import com.example.springkuzmin.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserService userService;
    @Autowired
    public PostService(PostRepository repository,
                       UserService userService){
        this.repository = repository;
        this.userService = userService;
    }
    public Post create(Blocks blocks){
        Post post = new Post();
        post.setTitle(blocks.getTitle());
        post.setCreatedAt(new Date());
        post.setAuthorId(userService
                .getByUsername(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName())
                .getId()
        );
        post.setBody(blocks.getBlocks());
        return repository.save(post);
    }
    public List<Post> getAllByAuthorId(UUID id){
        return repository.findAllByAuthorId(id);
    }
}
