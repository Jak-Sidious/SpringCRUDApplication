package com.example.SpringCrud.controller;

import com.example.SpringCrud.model.User;
import com.example.SpringCrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("users")
public class UserController {


    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public Object getAllUsers(){
        if(userService.getAllUsers().size() == 0){
            return "No Users Exist";
        }
        return userService.getAllUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser){
        return userService.createUser(newUser);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") @NotBlank Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") @NotBlank Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public Object updateUser(@RequestBody User updateUser, @PathVariable("id") @NotBlank Long id) {
        User leUserUpdate = userService.getUserById(id);
        if(leUserUpdate == null){
            return "User Doesn't exist";
        }
        leUserUpdate.setUserName(updateUser.getUserName());
        leUserUpdate.setAge(updateUser.getAge());
        return userService.updateUser(leUserUpdate);
    }
}
