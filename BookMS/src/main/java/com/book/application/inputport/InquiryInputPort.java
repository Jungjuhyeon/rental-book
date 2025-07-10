package com.book.application.inputport;


import com.book.application.outputport.BookOutPutPort;
import com.book.application.usecase.InquiryUsecase;
import com.book.domain.model.Book;
import com.book.framwork.web.dto.BookOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class InquiryInputPort  implements InquiryUsecase {
    private final BookOutPutPort bookOutPutPort;
    @Override
    public BookOutPutDTO getBookInfo(long bookNo) {
        Book loadBook = bookOutPutPort.loadBook(bookNo);
        return BookOutPutDTO.mapToDTO(loadBook);
    }
}
