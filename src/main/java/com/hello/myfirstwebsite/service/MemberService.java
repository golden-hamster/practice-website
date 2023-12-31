package com.hello.myfirstwebsite.service;

import com.hello.myfirstwebsite.domain.Member;
import com.hello.myfirstwebsite.dto.MemberCreateDto;
import com.hello.myfirstwebsite.dto.MemberDto;
import com.hello.myfirstwebsite.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Long join(Member member) {
//        Member member = Member.createMember(memberDto.getLoginId(),memberDto.getName(), memberDto.getPassword());
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findAllMember() {
        List<Member> members = memberRepository.findAll();
//        List<MemberDto> memberDtoList = new ArrayList<>();
//        for (Member member : members) {
//            MemberDto memberDto = convertToDto(member);
//            memberDtoList.add(memberDto);
//        }
        return members;
    }

    /**
     * 회원 조회
     */
    public Member findById(Long id) {
        Member findMember = memberRepository.findById(id).get();
//        MemberDto memberDto = new MemberDto(findMember.getLoginId(), findMember.getName(), findMember.getCreatedDate());

        return findMember;
    }

    public Member findByLoginId(String loginId) {
        Member findMember = memberRepository.findByLoginId(loginId).get();
//        MemberDto memberDto = new MemberDto(findMember.getLoginId(), findMember.getName(), findMember.getCreatedDate());

        return findMember;
    }

    /**
     * 회원 수정
     */
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setLoginId(name);
    }

    /**
     * 회원 삭제
     */
    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

//    public MemberDto convertToDto(Member member) {
//        MemberDto memberDto = new MemberDto(member.getLoginId(), member.getName(), member.getCreatedDate());
//        return memberDto;
//    }


}
