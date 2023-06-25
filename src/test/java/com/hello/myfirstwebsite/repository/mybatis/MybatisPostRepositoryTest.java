package com.hello.myfirstwebsite.repository.mybatis;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.dto.PostSearchCond;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
class MybatisPostRepositoryTest {

    @Autowired
    PostMapper postMapper;

    @Autowired
    EntityManager em;

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
            System.out.println("post1 = " + post1.getPostId());
        }

        Post findPost = postMapper.findById(post.getPostId()).get();
        System.out.println("findPost.getId() = " + findPost.getPostId());
        System.out.println("findPost.getCreatedDate() = " + findPost.getCreatedDate());
        System.out.println("findPost.getTitle() = " + findPost.getTitle());
    }

    @Test
    public void delete(){
        //given
        Member member = Member.createMember("test123", "TEST1", "123");
        Post post = Post.createPost(member.getId(), "test...", "This test is...");

        postMapper.save(post);

        System.out.println("post.getPostId() = " + post.getPostId());
        //when
        postMapper.delete(post.getPostId());
        Optional<Post> findPost = postMapper.findById(post.getPostId());
        //then

        Assertions.assertThat(findPost).isEmpty();

     }
}