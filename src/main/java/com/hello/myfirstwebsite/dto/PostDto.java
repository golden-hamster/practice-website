package com.hello.myfirstwebsite.dto;

import com.hello.myfirstwebsite.domain.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

//    @NotBlank
//    private Member member;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private LocalDateTime createdTime;

    @NotBlank
    private String author;
}
