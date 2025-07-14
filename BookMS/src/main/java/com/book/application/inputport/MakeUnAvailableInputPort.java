package com.book.application.inputport;

import com.book.application.outputport.BookOutPutPort;
import com.book.application.usecase.MakeUnAvailableUsecase;
import com.book.domain.model.Book;
import com.book.framwork.web.dto.BookOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MakeUnAvailableInputPort  implements MakeUnAvailableUsecase {
    private final BookOutPutPort bookOutPutPort;
    @Override
    public BookOutPutDTO unavailable(Long bookNo) {
        Book loadBook = bookOutPutPort.loadBook(bookNo);
        loadBook.makeUnAvailable();
        return BookOutPutDTO.mapToDTO(loadBook);
    }
}
