package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Find books by title
    List<Book> findByTitle(String title);

    // Find books by author
    List<Book> findByAuthor(String author);

    // Find books with title containing certain text (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String titlePart);
}