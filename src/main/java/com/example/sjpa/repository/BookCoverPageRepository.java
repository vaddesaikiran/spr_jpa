package com.example.sjpa.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sjpa.entity.BookCoverPage;

public interface BookCoverPageRepository extends JpaRepository<BookCoverPage, Long> {

    Optional<BookCoverPage> findById(Long id);
}
