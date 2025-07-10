package com.rentalbook.application.inputport;


import com.rentalbook.application.outputport.RentalCardOutputPort;
import com.rentalbook.application.usecase.OverdueItemUsercase;
import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.domain.model.vo.Item;
import com.rentalbook.framwork.web.dto.RentalCardOutputDTO;
import com.rentalbook.framwork.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OverDueItemInputPort implements OverdueItemUsercase {

    private final RentalCardOutputPort rentalCardOutputPort;


    @Override
    public RentalCardOutputDTO overDueItem(UserItemInputDTO rental) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(rental.userId)
                .orElseThrow(()->new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
        rentalCard.overdueItem(new Item(rental.getItemId(),rental.getItemTitle()));
        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}
