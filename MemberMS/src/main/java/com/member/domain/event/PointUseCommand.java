package com.member.domain.event;

import com.member.domain.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class PointUseCommand implements Serializable {
    private IDName idName;
    private long point;
}