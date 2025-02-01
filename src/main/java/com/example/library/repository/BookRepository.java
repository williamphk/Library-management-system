package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String titlePart, String author);

    List<Book> findByAvailableTrue();

    List<Book> findByCategory(String category);

    Optional<Book> findByIsbn(String isbn);
}