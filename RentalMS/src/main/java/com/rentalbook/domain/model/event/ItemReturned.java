package com.rentalbook.domain.model.event;

import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.Item;
import lombok.Getter;

@Getter
public class ItemReturned extends ItemRented{
    public ItemReturned(IdName idName, Item item, long point) {
        super(idName, item, point);
    }
}
