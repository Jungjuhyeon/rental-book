package com.rentalbook.application.usecase;


import com.rentalbook.framwork.web.dto.RentalCardOutputDTO;
import com.rentalbook.framwork.web.dto.UserItemInputDTO;

public interface OverdueItemUsercase {
    public RentalCardOutputDTO overDueItem(UserItemInputDTO rental) throws Exception;
}
