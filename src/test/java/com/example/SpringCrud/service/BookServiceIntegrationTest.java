package com.example.SpringCrud.service;


import static org.assertj.core.api.Assertions.assertThat;
import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

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

        Mockito.when(bookRepository.findByTitle(testBook.getTitle())).thenReturn(testBook);
    }

    @Test
    public void whenValidTitle_thenBookShouldBeFound(){
        String title = "Testing in SpringBoot";
        Book found = iBookService.getBookByTitle(title);

        assertThat(found.getTitle())
                .isEqualTo(title);
    }
}
