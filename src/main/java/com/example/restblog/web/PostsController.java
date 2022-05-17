package com.example.restblog.web;
import com.example.restblog.services.PostServices;
import com.example.restblog.data.Post;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    List<Post> posts = new ArrayList<>();

    private PostServices postServices;

    public PostsController(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping
    public List<Post> getAll() {
        posts.add(new Post(1L, "Micheal", "Stuff1"));
        posts.add(new Post(2L, "Ben", "Stuff2"));
        posts.add(new Post(3L, "Dave", "Stuff3"));
        return posts;
    }

    @GetMapping(value = "/")
    public List<Post> posts() {
        return postServices.getAllPosts();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {
        for (Post post : getAll()) {
            if (Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return new Post();
    }

    @PostMapping(value = "/post")
    public void createPost(@RequestBody Post post) {
        if (post.getDateCreated() == null)
            post.setDateCreated(new Date());
        postServices.insert(post);
    }

    @PutMapping("{id}")
    public void updatePost(@Parameter Long id, @RequestBody Post post){
        Post postToUpdate = new Post();
        postToUpdate.setId(id);
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setContent(post.getContent());
        System.out.println(post);
    }
    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id){
        System.out.println("remove post with: " + id);
    }
}
