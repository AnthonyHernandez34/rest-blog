package com.example.restblog.service;
import com.example.restblog.respository.PostRepositories;
import com.example.restblog.data.Post;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    private PostRepositories postRepositories;

    public PostService(PostRepositories postRepositories) {
        this.postRepositories = postRepositories;
    }

    public List<Post> getAllPosts(){
        return postRepositories.findAll();
    }
    public void insert(Post post){
        postRepositories.save(post);
    }
}