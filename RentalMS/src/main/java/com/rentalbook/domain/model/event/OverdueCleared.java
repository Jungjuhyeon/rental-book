package com.rentalbook.domain.model.event;

import com.rentalbook.domain.model.vo.IdName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OverdueCleared implements Serializable {
    private IdName idName;
    private long point;
}
