package com.hello.myfirstwebsite.service;

import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import com.hello.myfirstwebsite.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long save(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    public Post findById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    public List<Post> findAll(PostSearchCond cond) {
        List<Post> posts = postRepository.findAll(cond);
        return posts;
    }
}
