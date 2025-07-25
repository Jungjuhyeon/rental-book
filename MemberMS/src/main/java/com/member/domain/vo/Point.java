package com.member.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    public long pointValue ;
    public long addPoint(long point){
        this.setPointValue(this.pointValue + point);
        return this.pointValue;
    }
    public long removePoint(long point) throws Exception {
        if(point > this.pointValue) {
            throw new Exception("기존 보유 Point보다 적어 삭제할 수 없습니다."); }
        this.setPointValue(this.pointValue - point);
        return this.pointValue;
    }
    public static Point createPoint()
    {
        return new Point(0L);
    }

}
