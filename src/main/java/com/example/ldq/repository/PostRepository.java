package com.example.ldq.repository;

import com.example.ldq.entity.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{

	List<Post> findByUserId(Long userId);
}
