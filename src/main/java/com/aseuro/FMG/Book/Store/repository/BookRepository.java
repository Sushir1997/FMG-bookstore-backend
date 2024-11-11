package com.aseuro.FMG.Book.Store.repository;

import com.aseuro.FMG.Book.Store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByStatus(String status);
}
