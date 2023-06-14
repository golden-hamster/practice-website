package com.hello.myfirstwebsite.repository;

import com.hello.myfirstwebsite.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);

}
