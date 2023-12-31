package com.hello.myfirstwebsite.repository;

import com.hello.myfirstwebsite.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    Member findByLoginId(String loginId);
    Optional<Member> findByLoginId(String loginId);

}
