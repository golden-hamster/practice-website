package com.hello.myfirstwebsite.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private String loginId;

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    private LocalDateTime createdDate;

    public static Member createMember(String loginId, String name, String password) {
        Member member = new Member();
        member.loginId = loginId;
        member.name = name;
        member.password = password;
        member.createdDate = LocalDateTime.now();
        return member;
    }

}
