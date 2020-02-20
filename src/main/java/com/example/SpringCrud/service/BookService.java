package com.example.SpringCrud.service;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book newBook);
    Book update(Book updatedBook);
    Book deleteBook(Long id);
}

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
    public Book update(Book updatedBook) {
        return bookRepository.save(updatedBook);
    }

    @Override
    public Book createBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    public Book deleteBook(Long id) {
        bookRepository.deleteById(id);
        return null;
    }
}
