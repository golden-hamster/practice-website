package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.dto.MemberCreateDto;
import com.hello.myfirstwebsite.dto.MemberDto;
import com.hello.myfirstwebsite.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "loginId", required = false)Long memberId, Model model) {
        if (memberId == null) {
            return "home";
        }

        //로그인
        MemberDto loginMember = memberService.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";

    }

    @GetMapping("/member/create")
    public String createForm(@ModelAttribute MemberCreateDto memberDto) {

        return "create";
    }

    @PostMapping("/member/create")
    public String create(@Valid @ModelAttribute MemberCreateDto memberDto) {
        Long memberId = memberService.join(memberDto);
        MemberDto member = memberService.findById(memberId);
        String loginId = member.getLoginId();
        return "redirect:/member/" + loginId;
    }

    @GetMapping("/member/{loginId}")
    public String showMember(@PathVariable String loginId, Model model) {
        MemberDto member = memberService.findByLoginId(loginId);
        model.addAttribute("member", member);
        return "member";
    }

    @GetMapping("/members")
    public String showAllMembers(Model model) {
        List<MemberDto> members = memberService.findAllMember();
        model.addAttribute("members", members);
        return "members";
    }

    @PostMapping("/member/{loginId}/delete")
    public String deleteMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "redirect:/members";
    }


}
