package com.example.SpringCrud.repository;

import com.example.SpringCrud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
