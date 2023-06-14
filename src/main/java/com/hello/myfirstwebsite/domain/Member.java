package com.hello.myfirstwebsite.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Id
    private Long id;

    @NotBlank
    private String userName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotBlank
    private String password;

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    private LocalDateTime createdDate;

    public static Member createMember(String userName, String password) {
        Member member = new Member();
        member.userName = userName;
        member.password = password;
        member.createdDate = LocalDateTime.now();
        return member;
    }

}
