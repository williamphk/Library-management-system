package com.example.library.service.impl;

import com.example.library.model.BorrowRecord;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.BorrowRecordService;
import com.example.library.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;

    public BorrowRecordServiceImpl(BorrowRecordRepository borrowRecordRepository,
            BookRepository bookRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    @Override
    public Optional<BorrowRecord> getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id);
    }

    @Override
    public List<BorrowRecord> getBorrowRecordsByUserId(Long userId) {
        return borrowRecordRepository.findByUserId(userId);
    }

    @Override
    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord) {
        // Check if book is available
        var book = bookRepository.findById(borrowRecord.getBook().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (!book.getAvailable()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }

        // Set borrow date and expected return date
        borrowRecord.setBorrowDate(LocalDateTime.now());
        borrowRecord.setExpectedReturnDate(LocalDateTime.now().plusDays(14)); // 2 weeks loan period

        // Update book availability
        book.setAvailable(false);
        bookRepository.save(book);

        return borrowRecordRepository.save(borrowRecord);
    }

    @Override
    public BorrowRecord returnBook(Long id) {
        BorrowRecord record = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found"));

        if (record.getReturnDate() != null) {
            throw new IllegalStateException("Book already returned");
        }

        // Set return date
        record.setReturnDate(LocalDateTime.now());

        // Make book available again
        var book = record.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return borrowRecordRepository.save(record);
    }

    @Override
    public void deleteBorrowRecord(Long id) {
        if (!borrowRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Borrow record not found");
        }
        borrowRecordRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(Long recordId, Long userId) {
        return borrowRecordRepository.findById(recordId)
                .map(record -> record.getUser().getId().equals(userId))
                .orElse(false);
    }
}