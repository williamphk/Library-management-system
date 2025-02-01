package com.example.library.service;

import com.example.library.model.BorrowRecord;
import java.util.List;
import java.util.Optional;

public interface BorrowRecordService {
    List<BorrowRecord> getAllBorrowRecords();

    Optional<BorrowRecord> getBorrowRecordById(Long id);

    List<BorrowRecord> getBorrowRecordsByUserId(Long userId);

    BorrowRecord createBorrowRecord(BorrowRecord borrowRecord);

    BorrowRecord returnBook(Long id);

    void deleteBorrowRecord(Long id);

    boolean isOwner(Long recordId, Long userId);
}