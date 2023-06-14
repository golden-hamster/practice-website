package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.dto.MemberCreateDto;
import com.hello.myfirstwebsite.dto.ShowMemberDto;
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
        log.info("home controller");
        return "home";
    }

    @GetMapping("/create")
    public String createForm(@ModelAttribute MemberCreateDto memberDto) {
        log.info("createForm controller");
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute MemberCreateDto memberDto) {
        log.info("create controller");
        Long memberId = memberService.join(memberDto);
        return "redirect:/member/" + memberId;
    }

    @GetMapping("/member/{memberId}")
    public String showMember(@PathVariable Long memberId, Model model) {
        log.info("showMember Controller");
        ShowMemberDto member = memberService.findOne(memberId);
        model.addAttribute(member);
        return "member";
    }

    @GetMapping("/members")
    public String showAllMembers(Model model) {
        List<Member> members = memberService.findAllMember();
        model.addAttribute("members", members);
        return "members";
    }

    @PostMapping("/member/{memberId}/delete")
    public String deleteMember(@PathVariable Long memberId) {
        log.info("deleteMember Controller");
        memberService.delete(memberId);
        return "redirect:/";
    }


}
