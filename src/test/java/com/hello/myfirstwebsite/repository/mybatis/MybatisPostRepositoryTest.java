package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import com.hello.myfirstwebsite.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MybatisPostRepositoryTest {

    @Autowired
    PostMapper postMapper;


    @Test
    public void save(){
        //given
        Member member = Member.createMember("test123", "TEST1", "123");
        Post post = Post.createPost(member.getId(), "test...", "This test is...");
        PostSearchCond cond = new PostSearchCond();

        //when
        postMapper.save(post);

        //then
        List<Post> all = postMapper.findAll(cond);
        for (Post post1 : all) {
            System.out.println("post1 = " + post1.getId());
        }

        Post findPost = postMapper.findById(post.getId()).get();
        System.out.println("findPost.getId() = " + findPost.getId());
        System.out.println("findPost.getCreatedDate() = " + findPost.getCreatedDate());
        System.out.println("findPost.getTitle() = " + findPost.getTitle());
    }

}