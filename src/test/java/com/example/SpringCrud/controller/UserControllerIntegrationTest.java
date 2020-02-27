package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.User;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @Test
    public void givenUser_whenGetUsers_thenReturnJsonArray() throws Exception {
        User testUser = new User("Test User", 25);
        List<User> allUsers = Arrays.asList(testUser);

        given(userService.getAllUsers()).willReturn(allUsers);

        mvc.perform(get("/users")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userName", is(testUser.getUserName())));
    }

    @Test
    public void noUser_whenGetUsers_thenReturnString() throws Exception {
        String response = "No Users Exist";

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(response)));
    }

    @Test
    public void test_createUser_Success() throws Exception {
        User testUser = new User("Test User", 25);
        given(userService.createUser(testUser)).willReturn(testUser);

        mvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(testUser)))
            .andExpect(status().isOk());
    }

    @Test
    public void existingUser_deleteUser() throws Exception {
        User testUser = new User("Test User", 25);

        given(userService.getUserById(testUser.getUserId())).willReturn(testUser);
        given(userService.deleteUser(testUser.getUserId())).willReturn(null);

        mvc.perform(delete("/users/1"))
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
