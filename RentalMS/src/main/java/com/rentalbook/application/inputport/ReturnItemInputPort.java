package com.rentalbook.application.inputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rentalbook.application.outputport.EventOutputPort;
import com.rentalbook.application.outputport.RentalCardOutputPort;
import com.rentalbook.application.usecase.ReturnItemUsercase;
import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.domain.model.event.ItemReturned;
import com.rentalbook.domain.model.vo.Item;
import com.rentalbook.framwork.web.dto.RentalCardOutputDTO;
import com.rentalbook.framwork.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnItemInputPort implements ReturnItemUsercase {

    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws JsonProcessingException {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDto.userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        Item returnItem = new Item(returnDto.getItemId(), returnDto.getItemTitle());
        rentalCard.returnItem(returnItem, LocalDate.now());


        ItemReturned itemReturnEvent = RentalCard.createItemReturnEvent(rentalCard.getMember(),returnItem,10L);
        eventOutputPort.occurReturnEvent(itemReturnEvent);

        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}
