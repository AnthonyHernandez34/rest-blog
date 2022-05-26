package com.example.restblog.service;
import com.example.restblog.data.Category;
import com.example.restblog.data.User;
import com.example.restblog.dto.CreatePostDto;
import com.example.restblog.respository.CategoriesRepository;
import com.example.restblog.respository.PostRepositories;
import com.example.restblog.data.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepositories postsRepository;
    private final CategoriesRepository categoriesRepository;
    private final UserService userService;

    public PostService(
            PostRepositories postsRepository,
            CategoriesRepository categoriesRepository,
            UserService userService){
        this.postsRepository = postsRepository;
        this.categoriesRepository = categoriesRepository;
        this.userService = userService;
    }

    public List<Post> getPostList() { // TODO rename this to getAllPosts
        return postsRepository.findAll();
    }

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTitleLike(keyword);
    }

    // We need to associate posts and users here
    public void addPost(CreatePostDto dto, Post newPost, String username) {

        // get the User object who made the post
        User user = userService.getUserByUsername(username);

        newPost.setTitle(dto.getTitle());
        newPost.setContent(dto.getContent());

        // associate the post with the user object
        user.getPosts().add(newPost);
        // associate the *user* with the post object
        newPost.setUser(user);

        List<Category> categoriesToAdd = new ArrayList<>();

        for (String categoryName : dto.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(categoryName));
        }

        newPost.setCategories(categoriesToAdd);

        // TODO: call postsRepository.save(newPost)
        postsRepository.save(newPost);
    }

    public void updatePost(long postId, Post post) {
        Post postToUpdate = postsRepository.findById(postId).orElseThrow();

        // TODO: Safety first!
        if (post.getContent() != null && !post.getContent().isEmpty()) {
            postToUpdate.setContent(post.getContent());
        }
        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
            postToUpdate.setTitle(post.getTitle());
        }

        postsRepository.save(postToUpdate);
    }

    public void deletePostById(long id) {
        // TODO: change old code to postsRepository.deleteById(id)
        postsRepository.deleteById(id);
    }

}