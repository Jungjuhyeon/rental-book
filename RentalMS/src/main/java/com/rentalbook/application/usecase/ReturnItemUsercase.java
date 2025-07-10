package com.rentalbook.application.usecase;


import com.rentalbook.framwork.web.dto.RentalCardOutputDTO;
import com.rentalbook.framwork.web.dto.UserItemInputDTO;

public interface ReturnItemUsercase {
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception;
}
