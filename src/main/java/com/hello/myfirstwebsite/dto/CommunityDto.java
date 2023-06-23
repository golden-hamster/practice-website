package com.hello.myfirstwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityDto {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String loginId;

    private LocalDateTime createdDate;

    public CommunityDto(String title, String author, String loginId, LocalDateTime createdDate) {
        this.title = title;
        this.author = author;
        this.loginId = loginId;
        this.createdDate = createdDate;
    }
}
