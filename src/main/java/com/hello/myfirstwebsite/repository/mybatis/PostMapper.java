package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.PostDto;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    void save(Post post);

    void update(@Param("id") Long id, @Param("updateParam") PostDto postDto);

    Optional<Post> findById(Long id);

    List<Post> findALl(PostSearchCond postSearch);
}
