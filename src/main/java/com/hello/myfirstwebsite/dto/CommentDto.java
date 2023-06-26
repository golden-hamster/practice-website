package com.hello.myfirstwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private String description;

    private String author;

    private LocalDateTime createdDate;

    public CommentDto(String description, String author, LocalDateTime createdDate) {
        this.description = description;
        this.author = author;
        this.createdDate = createdDate;
    }
}
