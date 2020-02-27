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
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager userEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // Given
        User testUser = new User("TestUser", 28);
        userEntityManager.persist(testUser);
        userEntityManager.flush();

        // When
        User foundUser = userRepository.findByUserName(testUser.getUserName());

        // Then
        assertThat(foundUser.getUserName())
                .isEqualTo(testUser.getUserName());
    }

    @Test
    public void whenUserIsUpdated_thenFieldsShouldChange() {
        User testUser = new User("TestUser", 29);
        userEntityManager.persist(testUser);
        userEntityManager.flush();

        User foundUser = userRepository.findByUserName(testUser.getUserName());
        foundUser.setUserName("Updated User");
        foundUser.setAge(22);

        assertThat(foundUser.getAge())
                .isEqualTo(22);
        assertThat((foundUser.getUserId()))
                .isEqualTo(Long.valueOf(2));
        assertThat(foundUser.getBorrowedBooks())
                .isEqualTo(null);
    }
}
