package com.hello.myfirstwebsite.repository;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.CommentDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    void update(@Param("id") Long id, @Param("CommentDto") CommentDto commentDto);

    Optional<Comment> findById(Long commentId);

    List<Comment> findByPostId(Long postId);

    List<Comment> findAll();

    void delete(Long commentId);
}
