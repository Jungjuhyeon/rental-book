package com.member.application.outputport;


import com.member.domain.Member;
import com.member.domain.vo.IDName;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberOutPutPort {
    public Member loadMember(long memberNo);
    public  Member loadMemberByIdName(IDName idName);
    public Member saveMember(Member member);
}
