package com.hello.myfirstwebsite.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @Id
    private Long id;

    private String loginId;

    private String name;

    private String password;

    private LocalDateTime createdDate;


    /**
     * setter
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public static Member createMember(String loginId, String name, String password) {
        Member member = new Member();
        member.loginId = loginId;
        member.name = name;
        member.password = password;
        member.createdDate = LocalDateTime.now();
        return member;
    }

}
