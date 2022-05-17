package com.example.restblog.services;
import com.example.restblog.respository.PostRepositories;
import com.example.restblog.data.Post;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostServices {

    private PostRepositories postRepositories;

    public PostServices(PostRepositories postRepositories) {
        this.postRepositories = postRepositories;
    }

    public List<Post> getAllPosts(){
        return postRepositories.findAll();
    }
    public void insert(Post post){
        postRepositories.save(post);
    }
}
