package com.hello.myfirstwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityDto {

    private Long postId;

    private String title;

    private String author;

    private String loginId;

    private LocalDateTime createdDate;

    public CommunityDto(Long postId,String title, String author, String loginId, LocalDateTime createdDate) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.loginId = loginId;
        this.createdDate = createdDate;
    }
}
