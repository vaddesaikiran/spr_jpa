package com.example.sjpa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sjpa.entity.Book;
import com.example.sjpa.entity.BookCoverPage;
import com.example.sjpa.repository.BookCoverPageRepository;
import com.example.sjpa.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookCoverPageRepository bookCoverPageRepository;

    public Book createBookWithCover(Book book, BookCoverPage bookCoverPage) {
//        BookCoverPage savedCoverPage = bookCoverPageRepository.save(bookCoverPage);
        book.setBookCoverPage(bookCoverPage);
        return bookRepository.save(book);
    }


    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public BookCoverPage getBookCoverPageById(Long id) {
        return bookCoverPageRepository.findById(id).orElse(null);
    }  
}
