package com.member.domain;

import com.member.domain.vo.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MemberNo;
    @Embedded
    private IDName idName;
    @Embedded
    private PassWord password;
    @Embedded
    private Email email;
    @ElementCollection
    private List<Authority> authorites = new ArrayList<Authority>();
    @Embedded
    private Point point;

    public static Member registerMember(IDName idName,PassWord pwd,Email email){
        Member member = new Member();
        member.setIdName(idName);
        member.setPassword(pwd);
        member.setEmail(email);
        member.setPoint( Point.createPoint());
        member.addAuthority(new Authority(UserRole.USER));
        return member;
    }

    private void addAuthority(Authority authority) {
        this.authorites.add(authority);
    }


    public long savePoint(long point)
    {
        return this.point.addPoint(point);
    }
    public long usePoint(long point) throws Exception {
        return this.point.removePoint(point);
    }

    public Member login(IDName idNname, PassWord password){ return this;}
    public void logout(IDName idName){}

}
