package com.hello.myfirstwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private Long commentId;

    private String description;

    private String author;

    private String authorId;

    private LocalDateTime createdDate;

    public CommentDto(Long commentId, String description, String author, String authorId, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.description = description;
        this.author = author;
        this.authorId = authorId;
        this.createdDate = createdDate;
    }
}
