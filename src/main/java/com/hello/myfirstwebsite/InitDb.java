package com.hello.myfirstwebsite;

import com.hello.myfirstwebsite.domain.Comment;
import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.domain.Post;
import com.hello.myfirstwebsite.service.CommentService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit();

    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        //CommentService.save() test
        private final CommentService commentService;

        public void dbInit() {
            Member member1 = Member.createMember("test1", "testName1", "123");
            Member member2 = Member.createMember("test2", "testName2", "123");
            Member member3 = Member.createMember("test3", "testName3", "123");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            Post post1 = Post.createPost(member1.getId(), "Test title1", "desc is ...");
            Post post2 = Post.createPost(member2.getId(), "Test title2", "desc is ...");
            Post post3 = Post.createPost(member3.getId(), "Test title3", "desc is ...");

            em.persist(post1);
            em.persist(post2);
            em.persist(post3);

            Comment comment1 = Comment.createComment(member1.getId(), post1.getPostId(), "comment is...");
            Comment comment2 = Comment.createComment(member2.getId(), post1.getPostId(), "comment is...");
            Comment comment3 = Comment.createComment(member3.getId(), post1.getPostId(), "comment is...");

//            em.persist(comment1);
//            em.persist(comment2);
//            em.persist(comment3);
            commentService.save(comment1);
            commentService.save(comment2);
            commentService.save(comment3);
        }

    }

}

