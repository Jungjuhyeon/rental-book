package com.rentalbook.application.inputport;

import com.rentalbook.application.outputport.RentalCardOutputPort;
import com.rentalbook.application.usecase.InquiryUsecase;
import com.rentalbook.framwork.web.dto.RentItemOutputDTO;
import com.rentalbook.framwork.web.dto.RentalCardOutputDTO;
import com.rentalbook.framwork.web.dto.ReturnItemOutputDTO;
import com.rentalbook.framwork.web.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    @Override
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.UserId)
                .map(RentalCardOutputDTO::mapToDTO);
    }

    @Override
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.UserId)
                .map(loadCard -> loadCard.getRentalItemList()
                        .stream()
                        .map(RentItemOutputDTO::mapToDTO)
                        .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<ReturnItemOutputDTO>> getAllReturnItem(UserInputDTO
                                                                        userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.UserId)
                .map(loadCard -> loadCard.getReturnItemList()
                        .stream()
                        .map(ReturnItemOutputDTO::mapToDTO)
                        .collect(Collectors.toList()));
    }
}
