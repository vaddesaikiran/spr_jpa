package com.example.sjpa.service;

import java.util.List;

import com.example.sjpa.entity.Book;
import com.example.sjpa.entity.BookCoverPage;

public interface BookService {
	public Book createBookWithCover(Book book, BookCoverPage bookCoverPage);
	public Book getBookById(Long id);
	public List<Book> getAllBooks();
	public boolean deleteBook(Long id);
}
