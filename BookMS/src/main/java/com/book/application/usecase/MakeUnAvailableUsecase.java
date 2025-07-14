package com.book.application.usecase;

import com.book.framwork.web.dto.BookOutPutDTO;

public interface MakeUnAvailableUsecase {
    public BookOutPutDTO unavailable(Long bookNo);
}
