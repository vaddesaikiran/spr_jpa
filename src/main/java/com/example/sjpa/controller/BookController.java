package com.example.sjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sjpa.entity.Book;
import com.example.sjpa.entity.BookCoverPage;
import com.example.sjpa.service.BookServiceImpl;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    // Create a book with optional cover page
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        BookCoverPage bookCoverPage = book.getBookCoverPage();

        // If no cover page is provided, set a default cover image URL
        if (bookCoverPage == null || bookCoverPage.getCoverImageUrl() == null) {
            bookCoverPage = new BookCoverPage();
            bookCoverPage.setCoverImageUrl("https://example.com/cover.jpg"); // Default URL
        }

        Book savedBook = bookServiceImpl.createBookWithCover(book, bookCoverPage);
        return ResponseEntity.ok(savedBook);
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookServiceImpl.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // Get all books
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookServiceImpl.getAllBooks();
    }

    // Delete book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean isDeleted = bookServiceImpl.deleteBook(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Remove book cover page
    @PutMapping("/{id}/removecover")
    public ResponseEntity<Book> removeBookCover(@PathVariable Long id) {
        Book updatedBook = bookServiceImpl.removeBookCover(id);
        return ResponseEntity.ok(updatedBook);
    }
    
    // Get cover page by ID
    @GetMapping("/coverpage/{id}")
    public ResponseEntity<BookCoverPage> getBookCoverPageById(@PathVariable Long id) {
        BookCoverPage bookCoverPage = bookServiceImpl.getBookCoverPageById(id);
        if (bookCoverPage != null) {
            return ResponseEntity.ok(bookCoverPage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
