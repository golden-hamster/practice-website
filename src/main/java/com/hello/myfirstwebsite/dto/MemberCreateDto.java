package com.hello.myfirstwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberCreateDto {

    @NotBlank(message = "회원이름은 필수입니다")
    private String userName;

    @NotBlank(message = "패스워드는 필수입니다")
    private String password;
}
