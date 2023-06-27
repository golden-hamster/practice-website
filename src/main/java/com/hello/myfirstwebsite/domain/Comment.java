package com.hello.myfirstwebsite.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@EqualsAndHashCode
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private Long memberId;

    private Long postId;

    private String description;

    private LocalDateTime createdDate;

    public static Comment createComment(Long memberId, Long postId, String description) {
        Comment comment = new Comment();
        comment.memberId = memberId;
        comment.postId = postId;
        comment.description = description;
        comment.createdDate = LocalDateTime.now();
        return comment;
    }
}
