package com.hello.myfirstwebsite.repository;

import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.PostDto;
import com.hello.myfirstwebsite.dto.PostSearchCond;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    void update(Long id, PostDto postDto);

    Optional<Post> findById(Long id);

    List<Post> findALl(PostSearchCond postSearch);
}
