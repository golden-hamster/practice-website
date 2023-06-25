package com.hello.myfirstwebsite.config;

import com.hello.myfirstwebsite.repository.PostRepository;
import com.hello.myfirstwebsite.repository.mybatis.MybatisPostRepository;
import com.hello.myfirstwebsite.repository.mybatis.PostMapper;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@RequiredArgsConstructor
public class MyConfig {

//    private final PostMapper postMapper;

//    @Bean
    public PostRepository postRepository(PostMapper postMapper) {
        return new MybatisPostRepository(postMapper);
    }
}
