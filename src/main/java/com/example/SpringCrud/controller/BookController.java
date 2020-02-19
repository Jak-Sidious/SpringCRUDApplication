package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private  BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    Book createBook(@RequestBody Book newBook){
        return bookService.createBook(newBook);
    }
}
