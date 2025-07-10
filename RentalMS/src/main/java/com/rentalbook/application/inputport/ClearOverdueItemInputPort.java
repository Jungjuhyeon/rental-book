package com.rentalbook.application.inputport;

import com.rentalbook.application.outputport.RentalCardOutputPort;
import com.rentalbook.application.usecase.ClearOverdueItemUsecase;
import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.framwork.web.dto.ClearOverdueInfoDTO;
import com.rentalbook.framwork.web.dto.RentalResultOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClearOverdueItemInputPort implements ClearOverdueItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDTO.UserId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
        rentalCard.makeAvailableRental(clearOverdueInfoDTO.getPoint());
        return RentalResultOutputDTO.mapToDTO(rentalCard);
    }
}
