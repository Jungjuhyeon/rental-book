package com.book.application.usecase;

import com.book.framwork.web.dto.BookOutPutDTO;

public interface MakeAvailableUsecase {
    public BookOutPutDTO available(Long bookNo);
}
