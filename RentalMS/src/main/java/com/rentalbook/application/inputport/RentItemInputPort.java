package com.rentalbook.application.inputport;


import com.rentalbook.application.outputport.RentalCardOutputPort;
import com.rentalbook.application.usecase.RentItemUsecase;
import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.Item;
import com.rentalbook.framwork.web.dto.RentalCardOutputDTO;
import com.rentalbook.framwork.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentItemInputPort implements RentItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO rentItem(UserItemInputDTO rental) {
        RentalCard rentalCard =rentalCardOutputPort.loadRentalCard(rental.userId)
                .orElseGet(()-> RentalCard.createRentalCard(new
                        IdName(rental.getUserId(), rental.getUserNm())));

        // 대여할 아이템 생성 및 대여 처리
        Item newItem = new Item(rental.getItemId(), rental.getItemTitle());
        rentalCard.rentItem(newItem);
        return RentalCardOutputDTO.mapToDTO(rentalCard);

    }
}
