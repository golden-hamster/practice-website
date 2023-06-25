package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.PostDto;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import com.hello.myfirstwebsite.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisPostRepository implements PostRepository {

    private final PostMapper postMapper;

    @Override
    public Post save(Post post) {
        postMapper.save(post);

        return post;
    }

    @Override
    public void update(Long id, PostDto postDto) {
        postMapper.update(id, postDto);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postMapper.findById(id);
    }

    @Override
    public List<Post> findAll(PostSearchCond cond) {
        return postMapper.findAll(cond);
    }

    @Override
    public void delete(Long id) {
        postMapper.delete(id);
    }
}
