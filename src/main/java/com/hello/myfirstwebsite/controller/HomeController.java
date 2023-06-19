package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.LoginDto;
import com.hello.myfirstwebsite.dto.MemberCreateDto;
import com.hello.myfirstwebsite.dto.MemberDto;
import com.hello.myfirstwebsite.dto.PostDto;
import com.hello.myfirstwebsite.service.MemberService;
import com.hello.myfirstwebsite.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)LoginDto loginDto, Model model) {

        //세션이 없으면 home
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "home";
//        }

//        LoginDto loginMember = (LoginDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginDto == null) {
            return "home";
        }
        Member member = memberService.findByLoginId(loginDto.getLoginId());
        if (member == null) {
            return "home";
        }

        //세션이 유지되면 loginHome 으로 이동
        model.addAttribute("memberName", member.getName());
        return "loginHome";

    }

    @GetMapping("/member/create")
    public String createForm() {

        return "create";
    }

    @PostMapping("/member/create")
    public String create(@Valid @ModelAttribute MemberCreateDto memberDto) {
        Long memberId = memberService.join(memberDto);
        Member member = memberService.findById(memberId);
        String loginId = member.getLoginId();
        return "redirect:/member/" + loginId;
    }

    @GetMapping("/member/{loginId}")
    public String showMember(@PathVariable String loginId, Model model) {
        Member member = memberService.findByLoginId(loginId);
        MemberDto memberDto = convertToDto(member);
        model.addAttribute("member", memberDto);
        return "member";
    }

    @GetMapping("/members")
    public String showAllMembers(Model model) {
        List<Member> members = memberService.findAllMember();

        List<MemberDto> memberDtoList = new ArrayList<>();
        for (Member member : members) {
            MemberDto memberDto = convertToDto(member);
            memberDtoList.add(memberDto);
        }
        model.addAttribute("members", memberDtoList);
        return "members";
    }

    @PostMapping("/member/{loginId}/delete")
    public String deleteMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "redirect:/members";
    }

    @GetMapping("/member/{loginId}/createPost")
    public String createPostForm(@PathVariable String loginId, Model model) {

        Member member = memberService.findByLoginId(loginId);
        String name = member.getName();
        model.addAttribute("name", name);
        return "createPost";
    }

    @PostMapping("/member/{loginId}/createPost")
    public String createPost(@PathVariable String loginId, @ModelAttribute PostDto postDto) {

        Member member = memberService.findByLoginId(loginId);

        return "redirect:/";
    }

    public MemberDto convertToDto(Member member) {
        MemberDto memberDto = new MemberDto(member.getLoginId(), member.getName(), member.getCreatedDate());
        return memberDto;
    }


}
