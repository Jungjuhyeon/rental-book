package com.rentalbook.framwork.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserItemInputDTO {
    public String userId;
    public String userNm;
    public Integer itemId;
    public String itemTitle;
}
