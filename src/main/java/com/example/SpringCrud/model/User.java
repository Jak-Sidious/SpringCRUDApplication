package com.example.SpringCrud.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Column(name= "user_id")
    @Id @GeneratedValue
    private Long userId;

    @Column(name= "user_name")
    private String userName;

    @Column(name= "age")
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Book> borrowedBooks;

    public User() {
    }

    public User(String userName, int age){
        this.userName = userName;
        this.age = age;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Book borrow){
        borrowedBooks.add(borrow);
    }

    public void returnBook(Book borrow){
        borrowedBooks.remove(borrow);
    }
}
