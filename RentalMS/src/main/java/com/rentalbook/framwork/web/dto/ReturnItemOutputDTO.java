package com.rentalbook.framwork.web.dto;

import com.rentalbook.domain.model.vo.ReturnItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReturnItemOutputDTO {
    private Integer itemNo;
    private String itemTitle;
    private LocalDate returnDate;
    public static ReturnItemOutputDTO mapToDTO(ReturnItem returnItem)
    {
        ReturnItemOutputDTO rentItemOutputDTO = new ReturnItemOutputDTO();

        rentItemOutputDTO.setItemNo(returnItem.getRentalItem().getItem().getNo());

        rentItemOutputDTO.setItemTitle(returnItem.getRentalItem().getItem().getTitle())
        ;
        rentItemOutputDTO.setReturnDate(returnItem.getReturnDate());
        return rentItemOutputDTO;
    }
}