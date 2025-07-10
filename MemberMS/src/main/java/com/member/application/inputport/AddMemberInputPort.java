package com.member.application.inputport;


import com.member.application.outputport.MemberOutPutPort;
import com.member.application.usecase.AddMemberUsecase;
import com.member.domain.Member;
import com.member.domain.vo.Email;
import com.member.domain.vo.IDName;
import com.member.domain.vo.PassWord;
import com.member.framwork.web.dto.MemberInfoDTO;
import com.member.framwork.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class AddMemberInputPort implements AddMemberUsecase {
    private final MemberOutPutPort memberOutPutPort;

    @Override
    public MemberOutPutDTO addMember(MemberInfoDTO memberInfoDTO) {

        IDName idName = new IDName(memberInfoDTO.getId(),memberInfoDTO.getName());
        PassWord pwd = new PassWord(memberInfoDTO.getPassWord(),memberInfoDTO.getPassWord());
        Email email = new Email(memberInfoDTO.getEmail());
        Member addedMember = Member.registerMember(idName,pwd,email);

        Member savedMember = memberOutPutPort.saveMember(addedMember);

        return MemberOutPutDTO.mapToDTO(savedMember);
    }
}
