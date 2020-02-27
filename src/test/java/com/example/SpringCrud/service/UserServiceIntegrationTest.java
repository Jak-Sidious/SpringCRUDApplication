package com.example.SpringCrud.service;


import com.example.SpringCrud.model.Book;
import com.example.SpringCrud.model.User;
import com.example.SpringCrud.repository.BookRepository;
import com.example.SpringCrud.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public IUserService iUserService(){
            return new UserService();
        }
    }

    @Autowired
    private IUserService iUserService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void setup() {
        User testUser = new User("Test user", 25);
        User testUser1 = new User("Second Test User", 31);
        Book testBook = new Book("User test Book", "testing");
        List<User> allUsers = Arrays.asList(testUser, testUser1);
        Mockito.when(userRepository.findByUserName(testUser.getUserName())).thenReturn(testUser);
        Mockito.when(userRepository.findAll()).thenReturn(allUsers);
        Mockito.when(userRepository.findById(testUser1.getUserId())).thenReturn(java.util.Optional.of(testUser1));
        Mockito.when(bookRepository.findById(testBook.getId())).thenReturn(java.util.Optional.of(testBook));
        Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
    }

    @Test
    public void whenValidUsername_thenUserShouldBeFound(){
        String username = "Test user";
        User foundUser = iUserService.getUserByUsername(username);

        assertThat(foundUser.getUserName())
                .isEqualTo(username);
    }

    @Test
    public void whenUsersExists_thenReturnListOfUsers() {
        List<User> usersList = iUserService.getAllUsers();
        assertThat(usersList.size())
                .isEqualTo(2);
    }

    @Test
    public void whenUserExists_thenTheyCanBeDeleted() {
        String username = "Test user";
        User foundUser = iUserService.getUserByUsername(username);

        assertThat(iUserService.deleteUser(foundUser.getUserId()))
                .isEqualTo(null);
    }

    @Test
    public void whenUserExists_thenCanBeUpdated(){
        String username = "Test user";
        User foundUser = iUserService.getUserByUsername(username);
        foundUser.setUserName("Updated User");
        foundUser.setAge(23);

        iUserService.updateUser(foundUser.getUserId(), foundUser);
        assertThat(foundUser.getUserName())
                .isEqualTo("Updated User");

    }
}
