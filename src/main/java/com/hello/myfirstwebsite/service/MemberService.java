package com.hello.myfirstwebsite.service;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.dto.MemberCreateDto;
import com.hello.myfirstwebsite.dto.ShowMemberDto;
import com.hello.myfirstwebsite.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(MemberCreateDto memberDto) {
        Member member = Member.createMember(memberDto.getUserName(), memberDto.getPassword());
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findAllMember() {
        List<Member> findMember = memberRepository.findAll();
        return findMember;
    }

    /**
     * 회원 조회
     */
    public ShowMemberDto findOne(Long id) {
        Member findMember = memberRepository.findById(id).get();
        ShowMemberDto showMemberDto = new ShowMemberDto(findMember.getUserName(), findMember.getCreatedDate());

        return showMemberDto;
    }

    /**
     * 회원 수정
     */
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setUserName(name);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

}
