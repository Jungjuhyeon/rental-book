package com.book.application.inputport;


import com.book.application.outputport.BookOutPutPort;
import com.book.application.usecase.AddBookUsecase;
import com.book.domain.model.Book;
import com.book.domain.model.vo.Classfication;
import com.book.domain.model.vo.Location;
import com.book.domain.model.vo.Source;
import com.book.framwork.web.dto.BookInfoDTO;
import com.book.framwork.web.dto.BookOutPutDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddBookInputPort implements AddBookUsecase {
    private final BookOutPutPort bookOutPutPort;
    @Override
    public BookOutPutDTO addBook(BookInfoDTO bookInfoDTO) {
        Book book = Book.enterBook(
                bookInfoDTO.getTitle(),
                bookInfoDTO.getAuthor(),
                bookInfoDTO.getIsbn(),
                bookInfoDTO.getDescription(),
                bookInfoDTO.getPublicationDate(),
                Source.valueOf(bookInfoDTO.getSource()),
                Classfication.valueOf(bookInfoDTO.getClassfication()),
                Location.valueOf(bookInfoDTO.getLocation())
        );
        Book save = bookOutPutPort.save(book);
        return BookOutPutDTO.mapToDTO(save);
    }
}
