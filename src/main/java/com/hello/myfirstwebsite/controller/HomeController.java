package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.dto.MemberCreateDto;
import com.hello.myfirstwebsite.dto.MemberDto;
import com.hello.myfirstwebsite.service.MemberService;
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

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/member/create")
    public String createForm(@ModelAttribute MemberCreateDto memberDto) {

        return "create";
    }

    @PostMapping("/member/create")
    public String create(@ModelAttribute MemberCreateDto memberDto) {
        Long memberId = memberService.join(memberDto);
        MemberDto member = memberService.findById(memberId);
        String name = member.getName();
        return "redirect:/member/" + name;
    }

    @GetMapping("/member/{name}")
    public String showMember(@PathVariable String name, Model model) {
        MemberDto member = memberService.findByName(name);
        model.addAttribute("member", member);
        return "member";
    }

    @GetMapping("/members")
    public String showAllMembers(Model model) {
        List<MemberDto> members = memberService.findAllMember();
        model.addAttribute("members", members);
        return "members";
    }

    @PostMapping("/member/{memberId}/delete")
    public String deleteMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "redirect:/members";
    }


}
