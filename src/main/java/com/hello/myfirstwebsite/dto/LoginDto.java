package com.hello.myfirstwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    public LoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
