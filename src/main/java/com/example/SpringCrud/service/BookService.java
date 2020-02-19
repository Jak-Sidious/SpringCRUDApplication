package com.example.SpringCrud.service;

import com.example.SpringCrud.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface BookService {
    List<Book> getAll();
    Object getById(Long id);
    Object create(Object o);
    Object update(Object o);
    void delete(int id);
}
