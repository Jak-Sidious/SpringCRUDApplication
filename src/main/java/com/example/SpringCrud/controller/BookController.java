package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

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
    public Optional<Book> getSingleBook(@PathVariable("id") @NotBlank Long id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable("id") @NotBlank Long id) {
        return bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public Optional<Book> updateBook(@RequestBody Book updateBook, @PathVariable("id") @NotBlank Long id){
        return bookService.getBookById(id)
                .map(book -> {
                    book.setTitle(updateBook.getTitle());
                    book.setAuthor(updateBook.getAuthor());
                    return bookService.update(book);
                });

    }
}
