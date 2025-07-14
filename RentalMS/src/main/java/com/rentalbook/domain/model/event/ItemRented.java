package com.rentalbook.domain.model.event;

import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ItemRented implements Serializable {
    private IdName idName;
    private Item item;
    private long point;
}