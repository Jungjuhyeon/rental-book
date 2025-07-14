package com.rentalbook.application.inputport;

import com.rentalbook.application.outputport.EventOutputPort;
import com.rentalbook.application.outputport.RentalCardOutputPort;
import com.rentalbook.application.usecase.ClearOverdueItemUsecase;
import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.domain.model.event.OverdueCleared;
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
    private final EventOutputPort eventOutputPort;
    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDTO.userId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
        rentalCard.makeAvailableRental(clearOverdueInfoDTO.getPoint());

        OverdueCleared overdueClearedEvent = RentalCard.createOverdueCleardEvent(rentalCard.getMember(),clearOverdueInfoDTO.getPoint());
        eventOutputPort.occurOverdueClearedEvent(overdueClearedEvent);

        return RentalResultOutputDTO.mapToDTO(rentalCard);
    }
}
