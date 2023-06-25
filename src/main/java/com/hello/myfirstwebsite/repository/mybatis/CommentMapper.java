package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.CommentDto;
import com.hello.myfirstwebsite.dto.PostDto;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {

    void save(Comment comment);

    void update(@Param("id") Long id, @Param("CommentDto")CommentDto commentDto);

    Optional<Comment> findById(Long commentId);

    List<Comment> findAll();

    void delete(Long commentId);
}
