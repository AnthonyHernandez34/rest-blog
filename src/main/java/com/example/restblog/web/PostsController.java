package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.Post;
import com.example.restblog.respository.CategoriesRepository;
import com.example.restblog.respository.PostRepositories;
import com.example.restblog.respository.UsersRepository;
import com.example.restblog.service.EmailService;
import com.example.restblog.service.PostService;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final PostRepositories postRepository;
    private final UsersRepository userRepository;
    private final CategoriesRepository categoryRepository;
    private final EmailService emailService;
    private final UserService userService;
    private PostService postServices;

    private PostsController(PostRepositories postsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository, EmailService emailService, UserService userService, PostService postServices) {
        this.postRepository = postsRepository;
        this.userRepository = usersRepository;
        this.categoryRepository = categoriesRepository;
        this.emailService = emailService;
        this.postServices = postServices;
        this.userService = userService;
    }


    @GetMapping
    private List<Post> getAll() {
        return postRepository.findAll();
    }

    @GetMapping("{id}")
    private Optional<Post> getById(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PostMapping(value = "/post")
    public void createPost(@RequestBody Post post) {
        if (post.getDateCreated() == null)
            post.setDateCreated(new Date());
        postServices.insert(post);
    }

    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody Post newPost) {
        // Nice and clean, huh?
        userService.addPost(newPost, username);
        emailService.prepareAndSend(newPost, "sadasdasdasd","sadsadads");
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        for (Post post : userService.getPostList()) {
            if (post.getId().equals(id)) {
                post.setContent(updatedPost.getContent());
                post.setTitle(updatedPost.getTitle());
            }
        }
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        userService.deletePostById(id);
    }
}