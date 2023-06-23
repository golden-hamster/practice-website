package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.CommunityDto;
import com.hello.myfirstwebsite.dto.PostDto;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import com.hello.myfirstwebsite.service.MemberService;
import com.hello.myfirstwebsite.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/member/{loginId}/createPost")
    public String createPostForm(@PathVariable String loginId, Model model) {

        Member member = memberService.findByLoginId(loginId);
        String name = member.getName();
        model.addAttribute("loginId", loginId);
        model.addAttribute("name", name);
        return "createPost";
    }

    @PostMapping("/member/{loginId}/createPost")
    public String createPost(@PathVariable String loginId, @ModelAttribute PostDto postDto) {

        Member member = memberService.findByLoginId(loginId);
        Post post = Post.createPost(member.getId(), postDto.getTitle(), postDto.getDescription());
        postService.save(post);

        return "redirect:/community";
    }

    @GetMapping("/member/{loginId}/community/post/{postId}")
    public String postForm(@PathVariable(name = "loginId") String loginId, @PathVariable(name = "postId") Long postId, Model model) {

        Post findpost = postService.findById(postId);
        PostDto postDto = convertToPostDto(findpost);
        model.addAttribute("post", postDto);
        return "post";
    }

    @GetMapping("/member/{loginId}/community")
    public String community(@ModelAttribute PostSearchCond cond, @PathVariable String loginId,Model model) {

        List<Post> posts = postService.findAll(cond);

        List<CommunityDto> communityDtoList = new ArrayList<>();

        for (Post post : posts) {
            CommunityDto communityDto = convertToCommunityDto(post);
            communityDtoList.add(communityDto);
        }

        model.addAttribute("posts", communityDtoList);
        return "community";
    }

    public CommunityDto convertToCommunityDto(Post post) {
        Member findMember = memberService.findById(post.getMemberId());

        return new CommunityDto(post.getId(), post.getTitle(), findMember.getName(), findMember.getLoginId(), post.getCreatedDate());
    }

    public PostDto convertToPostDto(Post post) {
        Member findMember = memberService.findById(post.getMemberId());

        return new PostDto(post.getTitle(), post.getDescription(), findMember.getName());
    }

}
