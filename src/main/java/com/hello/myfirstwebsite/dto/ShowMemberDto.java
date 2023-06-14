package com.hello.myfirstwebsite.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowMemberDto {

    private String userName;

    private LocalDateTime createdDate;

    public ShowMemberDto(String userName, LocalDateTime createdDate) {
        this.userName = userName;
        this.createdDate = createdDate;
    }
}
