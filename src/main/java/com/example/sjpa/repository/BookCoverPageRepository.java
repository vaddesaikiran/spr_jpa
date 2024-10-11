package com.example.sjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sjpa.entity.BookCoverPage;

public interface BookCoverPageRepository extends JpaRepository<BookCoverPage, Long> {}