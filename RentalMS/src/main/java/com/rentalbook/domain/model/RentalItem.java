package com.rentalbook.domain.model;

import com.rentalbook.domain.model.vo.Item;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class RentalItem {
    @Embedded
    private Item item;
    private LocalDate rentDate;
    private boolean overdued;
    private LocalDate overdueDate; //반납 예정일

    public static RentalItem createRentalItem(Item item)
    {
        return new RentalItem(item,LocalDate.now(),false,LocalDate.now().plusDays(14));
    }


}
