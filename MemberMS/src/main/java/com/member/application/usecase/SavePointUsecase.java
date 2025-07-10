package com.member.application.usecase;


import com.member.domain.vo.IDName;
import com.member.framwork.web.dto.MemberOutPutDTO;

public interface SavePointUsecase {
    public MemberOutPutDTO savePoint(IDName idName, Long point);

}
