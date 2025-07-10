package com.book.framwork.web.dto;

import com.book.domain.model.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookOutPutDTO {

    private long bookNo;
    private String bookTitle;
    private String bookStatus;
    public static BookOutPutDTO mapToDTO(Book book)
    {
        BookOutPutDTO bookOutPutDTO = new BookOutPutDTO();
        bookOutPutDTO.setBookNo(book.getNo());
        bookOutPutDTO.setBookTitle(book.getTitle());
        bookOutPutDTO.setBookStatus(book.getBookStatus().toString());
        return bookOutPutDTO;
    }
}
