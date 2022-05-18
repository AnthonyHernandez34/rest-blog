package com.example.restblog.data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotEmpty
    @Size(max = 255)
    @Column(unique = true)
    public String username;

    public User(long id) {
        this.id = id;
    }

    public User() {

    }

    public User(String password) {
        this.password = password;
    }

    public User(Role role) {
        this.role = role;
    }

    public User(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @NotEmpty
    @Email
    @Size(max = 255)
    @Column(unique = true)
    public String email;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public Date createdAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Role role;


    public enum Role {USER, ADMIN}

    ;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}