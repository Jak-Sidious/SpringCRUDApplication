package com.example.SpringCrud.service;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.model.User;
import com.example.SpringCrud.repository.BookRepository;
import com.example.SpringCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }


    @Override
    public User updateUser(Long id, User updatedUser) {
        User sentId = userRepository.findById(id).orElse(null);
        sentId.setUserName(updatedUser.getUserName());
        sentId.setAge(updatedUser.getAge());
        return userRepository.save(sentId);
    }

    @Override
    public User deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public User borrowBook(Long userId, Long bookId) {
          User theBorrower = userRepository.findById(userId).orElse(null);
          if (theBorrower != null){
              Book toBorrow = bookRepository.findById(bookId).orElse(null);
              theBorrower.setBorrowedBooks(toBorrow);
              toBorrow.setUser(theBorrower);
              theBorrower.setBorrowedBooks(toBorrow);
              return userRepository.save(theBorrower);
          }
          return null;
    }

    @Override
    public User returnBook(Long userId, Long bookId) {
        User theBorrower = userRepository.findById(userId).orElse(null);
        if (theBorrower != null){
            Book toReturn = bookRepository.findById(bookId).orElse(null);
            theBorrower.returnBook(toReturn);
            toReturn.setUser(null);
            return userRepository.save(theBorrower);
        }
        return null;
    }
}
