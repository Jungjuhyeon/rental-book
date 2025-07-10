package com.book.application.usecase;


import com.book.framwork.web.dto.BookOutPutDTO;

public interface InquiryUsecase {
    public BookOutPutDTO getBookInfo(long bookNo);
}
