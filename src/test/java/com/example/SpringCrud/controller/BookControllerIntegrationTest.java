package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.service.BookService;
import com.example.SpringCrud.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;


    @Test
    public void givenBook_WhenGetBooks_thenReturnJsonArray() throws Exception {
        Book testBook = new Book("Testing in SpringBoot", "Testers collective");
        List<Book> allBooks = Arrays.asList(testBook);

        given(bookService.getAllBooks()).willReturn(allBooks);

        mvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(testBook.getTitle())));
    }

    @Test
    public void noBook_WhenGetBooks_thenReturnString() throws Exception {
        String response = "No Books available";

        mvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(response)));
    }

    @Test
    public void noBook_whenGetSingleBookCalled_thenReturnString() throws Exception {
        Book testBook = new Book("Testing in SpringBoot", "Testers collective");

        given(bookService.getBookById(testBook.getId())).willReturn(testBook);

        mvc.perform(get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("That Book is not available")));
    }

    @Test
    public void test_createBook_Success() throws Exception {
        Book testBook = new Book("Testing in SpringBoot", "Testers collective");
        given(bookService.createBook(testBook)).willReturn(testBook);

        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testBook)))
                .andExpect(status().isOk());
    }

    @Test
    public void existingBook_deleteBook() throws Exception {
        Book testBook = new Book("Testing in SpringBoot", "Testers collective");

        given(bookService.getBookById(testBook.getId())).willReturn(testBook);
        given(bookService.deleteBook(testBook.getId())).willReturn(null);

        mvc.perform(delete("/books/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
