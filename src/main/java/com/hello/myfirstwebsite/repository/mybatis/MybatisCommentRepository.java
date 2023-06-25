package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.dto.CommentDto;
import com.hello.myfirstwebsite.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MybatisCommentRepository implements CommentRepository {

    private final CommentMapper commentMapper;

    @Override
    public Comment save(Comment comment) {
        commentMapper.save(comment);

        return comment;
    }

    @Override
    public void update(Long id, CommentDto commentDto) {
        commentMapper.update(id, commentDto);
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return commentMapper.findById(commentId);
    }

    @Override
    public List<Comment> findAll() {
        return commentMapper.findAll();
    }

    @Override
    public void delete(Long commentId) {
        commentMapper.delete(commentId);
    }
}
