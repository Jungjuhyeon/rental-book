package com.book.application.outputport;

import com.book.domain.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOutPutPort {
    public Book loadBook(long bookNo);

    public Book save(Book book);
}
