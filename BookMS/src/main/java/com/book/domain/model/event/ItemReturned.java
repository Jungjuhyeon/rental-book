package com.book.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemReturned extends ItemRented {
    public ItemReturned(IdName idName, Item item, long point) {
        super(idName, item, point);
    }
}
