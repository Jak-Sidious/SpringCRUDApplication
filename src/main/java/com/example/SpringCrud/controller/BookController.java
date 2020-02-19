package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
public class BookController {

    @Autowired
    private  BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public Object getAllBooks() {
        if(bookService.getAllBooks().size() == 0){
            return "No Books available";
        }
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book newBook){
        return bookService.createBook(newBook);
    }

    @GetMapping("/books/{id}")
    public Book getSingleBook(@PathVariable("id") @NotBlank Long id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBook(@PathVariable("id") @NotBlank Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book updateBook, @PathVariable("id") @NotBlank Long id){
        Book leUpdate = bookService.getBookById(id);
        leUpdate.setTitle(updateBook.getTitle());
        leUpdate.setAuthor(updateBook.getAuthor());
        return bookService.update(leUpdate);
    }
}
