package com.rentalbook.application.inputport;

import com.rentalbook.application.outputport.EventOutputPort;
import com.rentalbook.application.outputport.RentalCardOutputPort;
import com.rentalbook.application.usecase.CompensationUsecase;
import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.domain.model.event.PointUseCommand;
import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CompensationInputPort implements CompensationUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;
    private final EventOutputPort eventOutputPort;
    @Override
    public RentalCard cancleRentItem(IdName idName, Item item) {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        rentalCard.cancelRentItem(item);
                        eventOutputPort.occurPointUseCommand(new PointUseCommand(idName, 10L));
                        return rentalCard;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("Rental card not found for ID: " + idName.getId()));
    }


    @Override
    public RentalCard cancleReturnItem(IdName idName, Item item, long point) {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        rentalCard.cancleReturnItem(item, point);
                        eventOutputPort.occurPointUseCommand(new PointUseCommand(idName, point));
                        return rentalCard;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("Rental card not found for ID: " + idName.getId()));
    }
    @Override
    public long cancleMakeAvailableRental(IdName idName, long point) {
        return rentalCardOutputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        return rentalCard.cancleMakeAvailableRental(point);
                        // 별도로 포인트 사용취소를 보상하기위한 이벤트는 발행하지 않음.
                    }
                    catch (Exception e) {
                            throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new NoSuchElementException("Rental card not found for ID: " + idName.getId()));
    }
}