package com.example.sjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sjpa.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}