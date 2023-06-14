package com.hello.myfirstwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

    @NotBlank(message = "회원이름은 필수입니다.")
    private String loginId;

    @NotBlank
    private String name;

    private LocalDateTime createdDate;

    public MemberDto(String loginId,String name, LocalDateTime createdDate) {
        this.loginId = loginId;
        this.name = name;
        this.createdDate = createdDate;
    }
}
