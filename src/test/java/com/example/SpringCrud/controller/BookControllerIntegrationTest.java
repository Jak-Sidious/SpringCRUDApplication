package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private final BookService bookService;

    @Autowired
    private BookControllerIntegrationTest(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    public void givenBook_WhenGetBooks_thenReturnJsonArray() throws Exception {
        Book testBook = new Book("Testing in SpringBoot", "Testers collective");
        List<Book> allBooks = Arrays.asList(testBook);

        given(bookService.getAllBooks()).willReturn(allBooks);

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(testBook.getTitle())));
    }
}
