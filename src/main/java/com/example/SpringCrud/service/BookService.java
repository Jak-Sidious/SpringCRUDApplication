package com.example.SpringCrud.service;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public Book createBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    public Book update(Long id) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
