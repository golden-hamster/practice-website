package com.hello.myfirstwebsite.service;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.dto.CommentDto;
import com.hello.myfirstwebsite.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void update(Long id, CommentDto commentDto) {
        commentRepository.update(id, commentDto);
    }

    public Optional<Comment> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    public void delete(Long commentId) {
        commentRepository.delete(commentId);
    }

}
