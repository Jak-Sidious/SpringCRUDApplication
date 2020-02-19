package com.example.SpringCrud.service;

import com.example.SpringCrud.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;


interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book newBook);
    Book update(Long id);
    void deleteBook(Long id);
}
