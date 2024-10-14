package com.example.sjpa.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "book_cover_page_id")
    private BookCoverPage bookCoverPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookCoverPage getBookCoverPage() {
        return bookCoverPage;
    }

    public void setBookCoverPage(BookCoverPage bookCoverPage) {
        this.bookCoverPage = bookCoverPage;
    }
}
