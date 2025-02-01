package com.example.library.service;

import com.example.library.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    List<Book> searchBooks(String keyword);

    Book createBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    List<Book> getAvailableBooks();

    List<Book> getBooksByCategory(String category);

    boolean isBookAvailable(Long id);

    void updateBookAvailability(Long id, boolean available);
}