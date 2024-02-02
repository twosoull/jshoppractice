package com.jshoppractice.jshoppractice.service;

import com.jshoppractice.jshoppractice.repository.MemberRepository;
import com.jshoppractice.jshoppractice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        validateDuplicationMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        List<Member> findMemberName = memberRepository.findByName(member.getName());
        if(!findMemberName.isEmpty()){
            new IllegalStateException("이미 등록 된 회원입니다.");
        }
    }

    public List<Member> findMembers(){

        return memberRepository.findAll();
    }
}
