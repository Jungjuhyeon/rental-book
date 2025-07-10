package com.member.application.usecase;


import com.member.framwork.web.dto.MemberOutPutDTO;

public interface InquiryMermberUsecase {
    public MemberOutPutDTO getMember(long memberNo);
}
