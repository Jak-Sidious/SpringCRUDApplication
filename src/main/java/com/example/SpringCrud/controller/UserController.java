package com.example.SpringCrud.controller;

import com.example.SpringCrud.SpringCrudApplication;
import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.model.User;
import com.example.SpringCrud.service.BookService;
import com.example.SpringCrud.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("users")
public class UserController {


    private final UserService userService;
    private final BookService bookService;

    @Autowired
    UserController(UserService userService, BookService bookService){
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping()
    public Object getAllUsers(){
        if(userService.getAllUsers().size() == 0){
            return "No Users Exist";
        }
        return userService.getAllUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser){
        return userService.createUser(newUser);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") @NotBlank Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") @NotBlank Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public Object updateUser(@RequestBody User updateUser, @PathVariable("id") @NotBlank Long id) {
        User leUserUpdate = userService.getUserById(id);
        if(leUserUpdate == null){
            return "User Doesn't exist";
        }
        leUserUpdate.setUserName(updateUser.getUserName());
        leUserUpdate.setAge(updateUser.getAge());
        return userService.updateUser(leUserUpdate);
    }

    @PutMapping("/{id}/borrow/{bookid}")
    public Object borrowBook(@PathVariable("id") @NotBlank Long id, @PathVariable("bookid") @NotBlank Long bookid) {
        User leUserUpdate = userService.getUserById(id);
        Book toBorrow = bookService.getBookById(bookid);
        leUserUpdate.getBorrowedBooks();
        if(leUserUpdate == null){
            return "User Doesn't exist";
        } else if (toBorrow == null){
            return "That Book is currently unavailable";
        }

        leUserUpdate.getBorrowedBooks().add(toBorrow);
        return userService.updateUser(leUserUpdate);
    }

    @PutMapping("/{id}/return/{bookid}")
    public Object returnBook(@PathVariable("id") @NotBlank Long id, @PathVariable("bookid") @NotBlank Long bookid){
        User leUserUpdate = userService.getUserById(id);
        Book toBorrow = bookService.getBookById(bookid);
        leUserUpdate.getBorrowedBooks();
        if(leUserUpdate == null){
            return "User Doesn't exist";
        } else if (toBorrow == null){
            return "That Book is currently unavailable";
        }
        leUserUpdate.getBorrowedBooks().remove(toBorrow);
        return userService.updateUser(leUserUpdate);
    }
}
