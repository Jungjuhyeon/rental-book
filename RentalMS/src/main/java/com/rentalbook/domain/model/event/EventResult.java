package com.rentalbook.domain.model.event;

import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventResult implements Serializable {
    private EventType eventType;
    private boolean isSuccessed;
    private IdName idName;
    private Item item;
    private long point;

    public static EventResult create(EventType eventType,IdName idName, Item item, long point){
        EventResult eventResult = new EventResult();
        eventResult.eventType = eventType;
        eventResult.idName = idName;
        eventResult.item = item;
        eventResult.point = point;
        return eventResult;
    }

    public EventResult success(){
        this.isSuccessed = true;
        return this;
    }

    public EventResult fail(){
        this.isSuccessed = false;
        return this;
    }
}