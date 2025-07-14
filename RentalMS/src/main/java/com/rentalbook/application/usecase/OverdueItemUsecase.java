package com.rentalbook.application.usecase;


import com.rentalbook.framwork.web.dto.RentalCardOutputDTO;
import com.rentalbook.framwork.web.dto.UserItemInputDTO;

public interface OverdueItemUsecase {
    public RentalCardOutputDTO overDueItem(UserItemInputDTO rental) throws Exception;
}
