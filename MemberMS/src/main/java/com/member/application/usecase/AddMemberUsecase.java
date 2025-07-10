package com.member.application.usecase;

import com.member.framwork.web.dto.MemberInfoDTO;
import com.member.framwork.web.dto.MemberOutPutDTO;

public interface AddMemberUsecase {
    public MemberOutPutDTO addMember(MemberInfoDTO memberInfoDTO);
}
