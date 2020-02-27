package com.example.SpringCrud.service;

import com.example.SpringCrud.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface IUserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User newUser);
    Object updateUser(Long id, User updatedUser);
    User deleteUser(Long id);
    User borrowBook(Long userId, Long bookId);
    User returnBook(Long userId, Long bookId);
    User getUserByUsername(String username);
}