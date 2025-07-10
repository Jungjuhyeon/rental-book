package com.member.application.inputport;


import com.member.application.outputport.MemberOutPutPort;
import com.member.application.usecase.SavePointUsecase;
import com.member.domain.Member;
import com.member.domain.vo.IDName;
import com.member.framwork.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SavePointInputPort implements SavePointUsecase {
    private final MemberOutPutPort memberOutPutPort;
    @Override
    public MemberOutPutDTO savePoint(IDName idName, Long point) {
        Member loadMember = memberOutPutPort.loadMemberByIdName(idName);
        loadMember.savePoint(point);
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
