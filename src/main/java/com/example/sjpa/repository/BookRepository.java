package com.example.sjpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sjpa.entity.Book;
import com.example.sjpa.entity.BookCoverPage;

public interface BookRepository extends JpaRepository<Book, Long> {

}