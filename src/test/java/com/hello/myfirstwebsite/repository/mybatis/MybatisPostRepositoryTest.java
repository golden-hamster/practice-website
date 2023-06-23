package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

        //when
//        Post savedPost = postRepository.save(post);
        postMapper.save(post);
        //then

//        Post findPost = postRepository.findById(member.getId()).get();
//        Assertions.assertThat(findPost).isEqualTo(savedPost);

    }

}