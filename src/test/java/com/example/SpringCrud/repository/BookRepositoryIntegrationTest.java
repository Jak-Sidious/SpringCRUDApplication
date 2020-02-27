package com.example.SpringCrud.repository;

import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByTitle_thenReturnEmployee() {
        // Given
        Book testBook = new Book("Testing in Spring", "Testers collective");
        entityManager.persist(testBook);
        entityManager.flush();

        // When
        Book found = bookRepository.findByTitle(testBook.getTitle());

        // Then
        assertThat(found.getTitle())
                .isEqualTo(testBook.getTitle());
    }

    @Test
    public void whenBookIsUpdated_thenItShouldChange(){
        // Given
        Book testBook = new Book("Testing in Spring", "Testers collective");
        entityManager.persist(testBook);
        entityManager.flush();

        // When
        Book found = bookRepository.findByTitle(testBook.getTitle());
        found.setTitle("UpdatedBook");
        found.setAuthor("Updated author");

        // Then
        assertThat(found.getTitle())
                .isEqualTo("UpdatedBook");
    }

    @Test
    public void whenBookIsBorrowed_thenUserIsUpdated(){
        // Given
        Book testBook = new Book("Testing in Spring", "Testers collective");
        entityManager.persist(testBook);
        entityManager.flush();

        User testUser = new User("Fred", 28);
        entityManager.persist(testUser);
        entityManager.flush();

        // When
        Book found = bookRepository.findByTitle(testBook.getTitle());
        found.setUser(testUser);

        assertThat(found.getUser())
                .isEqualTo(testUser);

    }

}