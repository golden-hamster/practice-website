package com.hello.myfirstwebsite.controller;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.*;
import com.hello.myfirstwebsite.service.CommentService;
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
    private final CommentService commentService;

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

        boolean loginUserFlag = findpost.getMemberId().equals(findMember.getId());

        List<Comment> comments = commentService.findByPostId(postId);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = convertToCommentDto(comment);
            commentDtoList.add(commentDto);
        }

        PostDto postDto = convertToPostDto(findpost);

        model.addAttribute("comments", commentDtoList);
        model.addAttribute("post", postDto);
        model.addAttribute("postId", postId);
        model.addAttribute("loginUserFlag", loginUserFlag);
        model.addAttribute("loginId",loginDto.getLoginId());
        return "post";
    }

    @PostMapping("/community/{postId}/comment")
    public String createComment(@PathVariable Long postId,
                                @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) LoginDto loginDto,
                                @ModelAttribute CommentDto commentDto) {
        Member findMember = memberService.findByLoginId(loginDto.getLoginId());
        Comment comment = Comment.createComment(findMember.getId(), postId, commentDto.getDescription());
        commentService.save(comment);
        return "redirect:/community/" + postId;
    }

    @PostMapping("/deleteComment")
    public String deleteComment(Long commentId, Long postId) {
        commentService.delete(commentId);
        return "redirect:/community/" + postId;
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

    public CommentDto convertToCommentDto(Comment comment) {
        Member findMember = memberService.findById(comment.getMemberId());
        return new CommentDto(comment.getCommentId() ,comment.getDescription(), findMember.getName(), findMember.getLoginId(), comment.getCreatedDate());
    }

}
