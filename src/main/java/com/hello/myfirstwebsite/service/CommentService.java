package com.hello.myfirstwebsite.service;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.dto.CommentDto;
import com.hello.myfirstwebsite.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void update(Long id, CommentDto commentDto) {
        commentRepository.update(id, commentDto);
    }

    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public void delete(Long commentId) {
        commentRepository.delete(commentId);
    }

}