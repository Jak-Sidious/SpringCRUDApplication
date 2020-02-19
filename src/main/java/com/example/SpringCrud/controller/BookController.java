package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private  BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    Object getAllBooks() {
        if(bookService.getAllBooks().size() == 0){
            return "No Books available";
        }
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    Book createBook(@RequestBody Book newBook){
        return bookService.createBook(newBook);
    }

    @GetMapping("/books/{id}")
    Book getSingleBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
}
