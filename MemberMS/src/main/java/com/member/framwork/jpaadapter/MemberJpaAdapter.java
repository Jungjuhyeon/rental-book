package com.member.framwork.jpaadapter;


import com.member.application.outputport.MemberOutPutPort;
import com.member.domain.Member;
import com.member.domain.vo.IDName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberOutPutPort {

    private final MemberRepository memberRepository;
    @Override
    public Member loadMember(long memberNo) {
        return memberRepository.findById(memberNo).get();    }

    @Override
    public Member loadMemberByIdName(IDName idName) {
        return memberRepository.findMemberByIdName(idName).get();
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
