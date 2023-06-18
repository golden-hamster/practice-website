package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.dto.LoginDto;
import com.hello.myfirstwebsite.service.LoginService;
import com.hello.myfirstwebsite.service.MemberService;
import com.hello.myfirstwebsite.session.SessionConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginDto loginDto) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        Member member = loginService.login(loginDto.getLoginId(), loginDto.getPassword());

        if (member == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }

//        Cookie idCookie = new Cookie("loginId",
//                String.valueOf(loginMember.getId()));
//        response.addCookie(idCookie);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginDto);


        //로그인 성공 처리
        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        //세션 삭제
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    public LoginDto convertToDto(Member member) {
        LoginDto loginDto = new LoginDto(member.getLoginId(), member.getPassword());

        return loginDto;
    }

}
