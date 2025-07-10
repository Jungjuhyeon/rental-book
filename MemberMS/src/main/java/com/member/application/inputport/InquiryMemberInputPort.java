package com.member.application.inputport;



import com.member.application.outputport.MemberOutPutPort;
import com.member.application.usecase.InquiryMermberUsecase;
import com.member.domain.Member;
import com.member.framwork.web.dto.MemberOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryMemberInputPort implements InquiryMermberUsecase {
    private final MemberOutPutPort memberOutPutPort;
    @Override
    public MemberOutPutDTO getMember(long memberNo) {
        Member loadMember = memberOutPutPort.loadMember(memberNo);
        return MemberOutPutDTO.mapToDTO(loadMember);
    }
}
