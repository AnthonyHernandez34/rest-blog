package com.example.restblog.service;


import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.respository.PostRepositories;
import com.example.restblog.respository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// @Service allows us to register and inject UserService into any other class we want using Spring's IoC Container
@Service
public class UserService {

    //    TODO: inject UsersRepository and PostsRepository into UserService class via constructor injection
    private final UsersRepository usersRepository;
    private final PostRepositories postsRepository;

    public UserService(UsersRepository usersRepository, PostRepositories postsRepository){
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }

    public List<User> getUsersList(){ // TODO: rename this 'getAllUsers'
        return usersRepository.findAll();
    }

    public List<Post> getPostList(){ // TODO rename this to getAllPosts
        return postsRepository.findAll();
    }

    // We need to associate posts and users here
    public void addPost(Post newPost, String username){

        // get the User object who made the post
        User user = getUserByUsername(username);

        // associate the post with the user object
        user.getPosts().add(newPost);
        // associate the *user* with the post object
        newPost.setUser(user);

        // TODO: call postsRepository.save(newPost)
        postsRepository.save(newPost);
    }

    // Taken from UsersController
    public User getUserById(Long id){
        // TODO: use usersRepository.findById(id).orElseThrow()
        return usersRepository.findById(id).orElseThrow(); //throws an exception if the user cannot be found by id
    }

    // Taken from UsersController
    public User getUserByUsername(String username){
        // TODO: don't forget to change this to usersRepository.findByUsername(username)
        return usersRepository.findByUsername(username);
    }

    public void deletePostById(long id){
        // TODO: change old code to postsRepository.deleteById(id)
        postsRepository.deleteById(id);
    }
}