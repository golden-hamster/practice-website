package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.CommunityDto;
import com.hello.myfirstwebsite.dto.LoginDto;
import com.hello.myfirstwebsite.dto.PostDto;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import com.hello.myfirstwebsite.service.MemberService;
import com.hello.myfirstwebsite.service.PostService;
import com.hello.myfirstwebsite.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/community/createPost")
    public String createPostForm(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)LoginDto loginDto,
                                 Model model) {

        Member member = memberService.findByLoginId(loginDto.getLoginId());
        String name = member.getName();
        model.addAttribute("name", name);
        return "createPost";
    }

    @PostMapping("/community/createPost")
    public String createPost(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)LoginDto loginDto,
                             @ModelAttribute PostDto postDto) {

        Member member = memberService.findByLoginId(loginDto.getLoginId());
        Post post = Post.createPost(member.getId(), postDto.getTitle(), postDto.getDescription());
        Long postId = postService.save(post);
        log.info("postId={}", postId);
        /**
         * redirectAttribute 추가해야됨
         */
        return "redirect:/community";
    }

    @GetMapping("/community/{postId}")
    public String postForm(@PathVariable(name = "postId") Long postId,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) LoginDto loginDto,
                           Model model) {
        Post findpost = postService.findById(postId);
        Member findMember = memberService.findByLoginId(loginDto.getLoginId());

        boolean loginUser = findpost.getMemberId().equals(findMember.getId());

        PostDto postDto = convertToPostDto(findpost);
        model.addAttribute("post", postDto);
        model.addAttribute("postId", postId);
        model.addAttribute("loginUser", loginUser);
        return "post";
    }

    @GetMapping("/community")
    public String community(@ModelAttribute PostSearchCond cond, Model model) {
        List<Post> posts = postService.findAll(cond);
        log.info("postId={}", posts.get(0).getPostId());
        List<CommunityDto> communityDtoList = new ArrayList<>();
        for (Post post : posts) {
            CommunityDto communityDto = convertToCommunityDto(post);
            communityDtoList.add(communityDto);
        }
        model.addAttribute("posts", communityDtoList);
        return "community";
    }

    @PostMapping("/deletePost")
    public String deletePost(Long postId) {
        postService.delete(postId);
        return "redirect:/community";
    }

    public CommunityDto convertToCommunityDto(Post post) {
        Member findMember = memberService.findById(post.getMemberId());
        return new CommunityDto(post.getPostId(), post.getTitle(), findMember.getName(), findMember.getLoginId(), post.getCreatedDate());
    }

    public PostDto convertToPostDto(Post post) {
        Member findMember = memberService.findById(post.getMemberId());

        return new PostDto(post.getTitle(), post.getDescription(), findMember.getName());
    }

}
