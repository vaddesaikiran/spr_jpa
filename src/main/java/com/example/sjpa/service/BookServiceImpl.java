package com.example.sjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sjpa.entity.Book;
import com.example.sjpa.entity.BookCoverPage;
import com.example.sjpa.repository.BookRepository;
import com.example.sjpa.repository.BookCoverPageRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCoverPageRepository bookCoverPageRepository;

    // Create a book with or without a cover
    @Transactional
    public Book createBookWithCover(Book book, BookCoverPage bookCoverPage) {
        book.setBookCoverPage(bookCoverPage);
        return bookRepository.save(book);
    }

    // Get book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Delete a book by ID
    @Transactional
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Remove cover page and trigger orphan removal
    @Transactional
    public Book removeBookCover(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookCoverPage(null);  // This will trigger orphan removal
        return bookRepository.save(book);  // Save the book without cover
    }

    // Get cover page by ID
    public BookCoverPage getBookCoverPageById(Long id) {
        return bookCoverPageRepository.findById(id).orElse(null);
    }
}
