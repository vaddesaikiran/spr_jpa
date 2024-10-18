package com.example.ldq.repository;

import com.example.ldq.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findPostsByDynamicCriteria(String title, Long userId);
}
