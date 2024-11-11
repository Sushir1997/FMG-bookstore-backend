package com.aseuro.FMG.Book.Store.controller;

import com.aseuro.FMG.Book.Store.entity.Book;
import com.aseuro.FMG.Book.Store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    // Endpoint to get all available books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllAvailableBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Endpoint to get book details by ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to purchase a book by ID
    @PostMapping("/purchase/{bookId}")
    public ResponseEntity<String> purchaseBook(@PathVariable Long bookId) {
        boolean isPurchased = bookService.purchaseBook(bookId);
        if (isPurchased) {
            return new ResponseEntity<>("Book purchased successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not available for purchase", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
}