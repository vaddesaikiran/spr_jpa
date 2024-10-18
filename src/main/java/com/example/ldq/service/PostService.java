package com.example.ldq.service;

import com.example.ldq.entity.Post;
import com.example.ldq.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    // Dynamic query using Criteria API
    public List<Post> findPostsByDynamicCriteria(String title, Long userId) {
        return postRepository.findPostsByDynamicCriteria(title, userId);
    }
}
