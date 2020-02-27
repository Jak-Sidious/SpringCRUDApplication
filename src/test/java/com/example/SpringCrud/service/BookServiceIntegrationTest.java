package com.example.SpringCrud.service;


import static org.assertj.core.api.Assertions.assertThat;
import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class BookServiceIntegrationTest {

    @TestConfiguration
    static class BookServiceTestContextConfiguration {

        @Bean
        public IBookService iBookService(){
            return new BookService();
        }
    }

    @Autowired
    private IBookService iBookService;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        Book testBook = new Book("Testing in SpringBoot", "Tester Collective");
        Book testBook1 = new Book("ThatBook", "Book collectors");
        List<Book> allBooks = Arrays.asList(testBook,testBook1);
        Long book1Id = testBook.getId();
        Mockito.when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(testBook);
        Mockito.when(bookRepository.findAll()).thenReturn(allBooks);
        Mockito.when(bookRepository.findById(book1Id)).thenReturn(java.util.Optional.of(testBook));
    }

    @Test
    public void whenValidTitle_thenBookShouldBeFound(){
        String title = "Testing in SpringBoot";
        Book found = iBookService.getBookByTitle(title);

        assertThat(found.getTitle())
                .isEqualTo(title);
    }

    @Test
    public void whenBooksExists_thenListOfBooksShouldBeReturned() {
        List<Book> bookList = iBookService.getAllBooks();
        assertThat(bookList.size())
                .isEqualTo(2);
    }

    @Test
    public void whenBookExists_thenCanBeUpdated(){
        String title = "Testing in SpringBoot";
        Book found = iBookService.getBookByTitle(title);
        found.setTitle("Update");
        found.setAuthor("Author");
        iBookService.update(found);

        assertThat(found.getTitle())
                .isEqualTo("Update");
    }

    @Test
    public void whenBookExists_thenItCanBeDeleted(){
        String title = "Testing in SpringBoot";
        Book found = iBookService.getBookByTitle(title);
        iBookService.deleteBook(found.getId());

        assertThat(iBookService.deleteBook(found.getId()))
                .isEqualTo(null);
    }
}
