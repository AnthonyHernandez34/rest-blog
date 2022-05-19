package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
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
        users.add(new User(1,"anthony", "ants.hernandez@gmail.com", "password1"));
        users.add(new User(2,"david", "david@gmail.com", "password2"));
        users.add(new User(3,"ben", "ben@gmail.com", "password3"));

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

    @GetMapping("/username")
    private User getByUsername(@RequestParam String username) {
        return null;
    }

    @GetMapping("/email")
    private User geyByEmail(@RequestParam String email) {
        return null;
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

    @PutMapping("{id}/updatePassword")
    public void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        User userToUpdate = getUserById(id);
        userToUpdate.setPassword(newPassword);
        System.out.println(userToUpdate.getPassword());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("Deleting chosen id: " + id);
    }
}