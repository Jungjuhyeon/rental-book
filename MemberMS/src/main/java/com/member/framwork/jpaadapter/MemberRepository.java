package com.member.framwork.jpaadapter;



import com.member.domain.Member;
import com.member.domain.vo.IDName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    public Optional<Member> findMemberByIdName(IDName idName);
}
