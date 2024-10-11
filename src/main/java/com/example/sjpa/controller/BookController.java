package com.example.sjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sjpa.service.BookServiceImpl;
import com.example.sjpa.entity.Book;
import com.example.sjpa.entity.BookCoverPage;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        BookCoverPage bookCoverPage = new BookCoverPage();
        bookCoverPage.setCoverImageUrl("https://example.com/cover.jpg");

        Book savedBook = bookServiceImpl.createBookWithCover(book, bookCoverPage);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookServiceImpl.getBookById(id);
        return ResponseEntity.ok(book);
    }
    
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookServiceImpl.getAllBooks();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean isDeleted = bookServiceImpl.deleteBook(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
