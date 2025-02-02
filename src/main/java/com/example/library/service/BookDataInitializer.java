package com.example.library.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import com.example.library.repository.BookRepository;
import com.example.library.model.Book;
import java.util.Arrays;
import java.util.List;

@Service
public class BookDataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookDataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            initializeBooks();
        }
    }

    public void initializeBooks() {
        List<Book> books = Arrays.asList(
                Book.builder()
                        .title("The Great Gatsby")
                        .author("F. Scott Fitzgerald")
                        .isbn("9780743273565")
                        .publicationYear(1925)
                        .genre("FICTION")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("To Kill a Mockingbird")
                        .author("Harper Lee")
                        .isbn("9780446310789")
                        .publicationYear(1960)
                        .genre("FICTION")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("1984")
                        .author("George Orwell")
                        .isbn("9780451524935")
                        .publicationYear(1949)
                        .genre("SCIENCE_FICTION")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("The Hobbit")
                        .author("J.R.R. Tolkien")
                        .isbn("9780547928227")
                        .publicationYear(1937)
                        .genre("FANTASY")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("Pride and Prejudice")
                        .author("Jane Austen")
                        .isbn("9780141439518")
                        .publicationYear(1813)
                        .genre("ROMANCE")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("The Da Vinci Code")
                        .author("Dan Brown")
                        .isbn("9780307474278")
                        .publicationYear(2003)
                        .genre("THRILLER")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("The Art of Computer Programming")
                        .author("Donald Knuth")
                        .isbn("9780201896831")
                        .publicationYear(1968)
                        .genre("TECHNICAL")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("A Brief History of Time")
                        .author("Stephen Hawking")
                        .isbn("9780553380163")
                        .publicationYear(1988)
                        .genre("SCIENCE")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("The Catcher in the Rye")
                        .author("J.D. Salinger")
                        .isbn("9780316769488")
                        .publicationYear(1951)
                        .genre("FICTION")
                        .available(true)
                        .build(),

                Book.builder()
                        .title("Harry Potter and the Sorcerer's Stone")
                        .author("J.K. Rowling")
                        .isbn("9780590353427")
                        .publicationYear(1997)
                        .genre("FANTASY")
                        .available(true)
                        .build());

        bookRepository.saveAll(books);
    }
}