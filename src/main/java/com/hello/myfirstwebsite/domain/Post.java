package com.hello.myfirstwebsite.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String description;

    private LocalDateTime createdTime;


    public static Post createPost(Member member, String title, String description) {
        Post post = new Post();
        post.member = member;
        post.title = title;
        post.description = description;
        post.createdTime = LocalDateTime.now();
        return post;
    }

}
