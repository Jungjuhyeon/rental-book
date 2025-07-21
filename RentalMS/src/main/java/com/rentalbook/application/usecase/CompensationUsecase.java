package com.rentalbook.application.usecase;

import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.Item;

public interface CompensationUsecase {
    public RentalCard cancleRentItem(IdName idName, Item item);
    public RentalCard cancleReturnItem(IdName idName,Item item, long point) throws Exception;
    public long cancleMakeAvailableRental(IdName idName,long point);
}
