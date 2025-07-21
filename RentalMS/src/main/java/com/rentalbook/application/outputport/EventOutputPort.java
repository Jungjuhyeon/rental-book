package com.rentalbook.application.outputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rentalbook.domain.model.event.ItemRented;
import com.rentalbook.domain.model.event.ItemReturned;
import com.rentalbook.domain.model.event.OverdueCleared;
import com.rentalbook.domain.model.event.PointUseCommand;

public interface EventOutputPort {
    public void occurRentalEvent(ItemRented rentalItem) throws JsonProcessingException;
    public void occurReturnEvent(ItemReturned itemReturned) throws JsonProcessingException;
    public void occurOverdueClearedEvent(OverdueCleared overdueCleared) throws JsonProcessingException;
    public void occurPointUseCommand(PointUseCommand pointUseCommand) throws JsonProcessingException;
}