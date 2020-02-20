package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    @Autowired
    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping()
    public Object getAllBooks() {
        if(bookService.getAllBooks().size() == 0){
            return "No Books available";
        }
        return bookService.getAllBooks();
    }

    @PostMapping()
    public Book createBook(@RequestBody Book newBook){
        return bookService.createBook(newBook);
    }

    @GetMapping("/{id}")
    public Object getSingleBook(@PathVariable("id") @NotBlank Long id) {
        if(bookService.getBookById(id) == null){
            return "That Book is not available";
        }

        return bookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable("id") @NotBlank Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public Object updateBook(@RequestBody Book updateBook, @PathVariable("id") @NotBlank Long id){
        Book leBookUpdate = bookService.getBookById(id);
        if(leBookUpdate == null){
            return "That Book doesn't exist";
        }
        leBookUpdate.setTitle(updateBook.getTitle());
        leBookUpdate.setAuthor(updateBook.getAuthor());
        return bookService.update(leBookUpdate);
    }
}
