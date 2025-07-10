package com.rentalbook.application.usecase;

import com.rentalbook.framwork.web.dto.ClearOverdueInfoDTO;
import com.rentalbook.framwork.web.dto.RentalResultOutputDTO;

public interface ClearOverdueItemUsecase {
    RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO)
            throws Exception;
}