package com.member.application.inputport;


import com.member.application.outputport.MemberOutPutPort;
import com.member.application.usecase.UsePointUsecase;
import com.member.domain.Member;
import com.member.domain.vo.IDName;
import com.member.framwork.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UsePointInputPort implements UsePointUsecase {
    private final MemberOutPutPort memberOutPutPort;
    @Override
    public MemberOutPutDTO userPoint(IDName idName, long point) throws Exception {
        Member loadMember = memberOutPutPort.loadMemberByIdName(idName);
        loadMember.usePoint(point);
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
