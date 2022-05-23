package com.example.restblog.respository;
import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositories extends JpaRepository<Post,Long> {
}
