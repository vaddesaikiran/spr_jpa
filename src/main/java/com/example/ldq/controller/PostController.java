package com.example.ldq.controller;

import com.example.ldq.entity.Post;
import com.example.ldq.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            post.getUser().getId();
        }
        return post;
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }

 
    @GetMapping("/search")
    public List<Post> searchPosts(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long userId
    ) {
        return postService.findPostsByDynamicCriteria(title, userId);
    }
}
