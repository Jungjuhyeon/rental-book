package com.book.application.usecase;


import com.book.framwork.web.dto.BookInfoDTO;
import com.book.framwork.web.dto.BookOutPutDTO;

public interface AddBookUsecase {
    public BookOutPutDTO addBook(BookInfoDTO bookInfoDTO);
}
