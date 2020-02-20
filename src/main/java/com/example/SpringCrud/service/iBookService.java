package com.example.SpringCrud.service;

import com.example.SpringCrud.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book newBook);
    Book update(Book updatedBook);
    Book deleteBook(Long id);
}
