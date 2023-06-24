package com.hello.myfirstwebsite.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    private String title;

    private String description;

    private LocalDateTime createdDate;


    public static Post createPost(Long memberId, String title, String description) {
        Post post = new Post();
        post.memberId = memberId;
        post.title = title;
        post.description = description;
        post.createdDate = LocalDateTime.now();
        return post;
    }

}
