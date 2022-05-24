package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.service.PostService;
import com.example.restblog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final UserService userService;
    private PostService postServices;

    public PostsController(UserService userService,PostService postServices) {
        this.userService = userService;
        this.postServices = postServices;
    }

    @GetMapping
    public List<Post> getAll() {
        return userService.getPostList();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {

        // TODO: refactor this all out of here
        for (Post post : userService.getPostList()) {
            if (Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return null;
    }

    @PostMapping(value = "/post")
    public void createPost(@RequestBody Post post) {
        if (post.getDateCreated() == null)
            post.setDateCreated(new Date());
        postServices.insert(post);
    }
    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody Post newPost){
        // Nice and clean, huh?
        userService.addPost(newPost, username);
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        for (Post post : userService.getPostList()){
            if (post.getId().equals(id)){
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
