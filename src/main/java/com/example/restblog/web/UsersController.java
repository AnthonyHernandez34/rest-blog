package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.restblog.data.User.Role.ADMIN;
import static com.example.restblog.data.User.Role.USER;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAll() {
        users.add(new User("anthony", "ants.hernandez@gmail.com", "password1"));
        users.add(new User("david", "david@gmail.com", "password2"));
        users.add(new User("ben", "ben@gmail.com", "password3"));

        return users;
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        for (User user : getAll()) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return new User();
    }

    @PostMapping
    public void createUser(@RequestBody User userToAdd) {
        System.out.println(userToAdd);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId() == (id)) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword());
                user.setCreatedAt(updatedUser.getCreatedAt());
                user.setRole(updatedUser.getRole());
            }
        }
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("Deleting chosen id: " + id);
    }
}