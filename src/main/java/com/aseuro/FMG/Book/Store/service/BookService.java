package com.aseuro.FMG.Book.Store.service;

import com.aseuro.FMG.Book.Store.entity.Book;
import com.aseuro.FMG.Book.Store.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllAvailableBooks() {
        return bookRepository.findByStatus("available");
    }

    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    @Transactional
    public boolean purchaseBook(Long bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent() && "available".equals(bookOpt.get().getStatus())) {
            Book book = bookOpt.get();
            book.setStatus("sold");
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public Book saveBook(Book book) {
        // Ensure the book is set to "available" status when saved
        book.setStatus("available");
        return bookRepository.save(book);
    }
}

