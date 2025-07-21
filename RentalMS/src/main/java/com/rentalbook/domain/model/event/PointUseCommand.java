package com.rentalbook.domain.model.event;

import com.rentalbook.domain.model.vo.IdName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class PointUseCommand implements Serializable {
    private IdName idName;
    private long point;
}