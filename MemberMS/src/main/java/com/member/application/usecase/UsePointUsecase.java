package com.member.application.usecase;

import com.member.domain.vo.IDName;
import com.member.framwork.web.dto.MemberOutPutDTO;

public interface UsePointUsecase {
    public MemberOutPutDTO userPoint(IDName idName, long point) throws Exception;
}
