package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.service.MemberService;
import com.hello.myfirstwebsite.service.PostService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MybatisCommentRepositoryTest {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    MemberService memberService;

    @Autowired
    PostService postService;

    @Test
    public void save(){
        //given
        Member member = Member.createMember("test123", "TEST1", "123");
        Post post = Post.createPost(member.getId(), "test...", "This test is...");
        Comment comment1 = Comment.createComment(member.getId(), post.getPostId(), "comment1 is...");
        Comment comment2 = Comment.createComment(member.getId(), post.getPostId(), "comment2 is...");


        //when
        System.out.println("post.getPostId() = " + post.getPostId());
        commentMapper.save(comment1);
        commentMapper.save(comment2);
        List<Comment> comments = commentMapper.findByPostId(post.getPostId());
        Comment findComment = commentMapper.findById(comment1.getCommentId()).get();

        //then
        Assertions.assertThat(comments).isEmpty();
        System.out.println("findComment.getDescription() = " + findComment.getDescription());
        for (Comment comment : comments) {
            System.out.println("comment.getDescription() = " + comment.getDescription());
        }
     }
}