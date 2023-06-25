package com.hello.myfirstwebsite.service;

import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import com.hello.myfirstwebsite.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost.getPostId();
    }

    @Transactional
    public void delete(Long postId) {
        postRepository.delete(postId);
    }

    public Post findById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    public List<Post> findAll(PostSearchCond cond) {
        List<Post> posts = postRepository.findAll(cond);
        log.info("postId={}", posts.get(0).getPostId());
        return posts;
    }
}
