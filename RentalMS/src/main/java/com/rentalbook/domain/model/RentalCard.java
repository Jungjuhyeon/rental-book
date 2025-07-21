package com.rentalbook.domain.model;

import com.rentalbook.domain.model.event.ItemRented;
import com.rentalbook.domain.model.event.ItemReturned;
import com.rentalbook.domain.model.event.OverdueCleared;
import com.rentalbook.domain.model.vo.*;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCard {

    @EmbeddedId
    private RentalCardNo rentalCardNo;

    @Embedded
    private IdName member;
    private RentStatus rentStatus;
    @Embedded
    private LateFee lateFee;
    @ElementCollection
    private List<RentalItem> rentalItemList = new ArrayList<RentalItem>();
    @ElementCollection
    private List<ReturnItem> returnItemList = new ArrayList<ReturnItem>();

    private void addRentalItem(RentalItem rentalItem){
        this.rentalItemList.add(rentalItem);
    }
    private void removeRentalItem(RentalItem rentalItem){this.rentalItemList.remove(rentalItem);}
    private void addReturnItem(ReturnItem returnItem){this.returnItemList.add(returnItem);}
    private void removeReturnItem(ReturnItem returnItem){this.returnItemList.remove(returnItem);}

    //대여카드 생성
    public static RentalCard createRentalCard(IdName creator){
        RentalCard rentalCard = new RentalCard();
        rentalCard.setRentalCardNo(RentalCardNo.createRentalCardNo());
        rentalCard.setMember(creator);
        rentalCard.setRentStatus(RentStatus.RENT_AVAILABLE);
        rentalCard.setLateFee(LateFee.createLateFee());
        return rentalCard;
    }

    //이벤트 생성
    public static ItemRented createItemRentedEvent(IdName idName, Item item, long point){
        return new ItemRented(idName,item,point);
    }
    public static ItemReturned createItemReturnEvent(IdName idName, Item item, long point){
        return new ItemReturned(idName,item,point);
    }
    public static OverdueCleared createOverdueCleardEvent(IdName idName, long point) {
        return new OverdueCleared(idName,point);
    }

    //대여처리
    public RentalCard rentItem(Item item){
        checkRentalAvailable();
        this.addRentalItem(RentalItem.createRentalItem(item));
        return this;
    }
    //취소처리
    public RentalCard cancelRentItem(Item item) throws Exception {
        RentalItem rentedItem = this.rentalItemList.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
        this.removeRentalItem(rentedItem);
        return this;
    }

    private void checkRentalAvailable() {
        if(this.rentStatus == RentStatus.RENT_UNAVAILABLE)throw new IllegalArgumentException("대여불가상태입니다.");
        if(this.rentalItemList.size()>5) throw new IllegalArgumentException("이미 5권을 대여했습니다.");
    }

    public RentalCard returnItem(Item item, LocalDate returnDate){
        RentalItem rentalItem = this.rentalItemList.stream().filter(i->i.getItem().equals(item)).findFirst().get();
        calcalateLateFee(rentalItem,returnDate);
        this.addReturnItem(ReturnItem.createReturnItem(rentalItem));
        this.removeRentalItem(rentalItem);
        return this;
    }

    //반납취소
    public RentalCard cancleReturnItem(Item item,long point) throws Exception {
        //reternItem의 getitem은 rentItem이므로 여기서 getItem을 해야 Item과 비교 가능
        ReturnItem returnItem = this.returnItemList.stream().filter(i ->
                i.getRentalItem().getItem().equals(item)).findFirst().get();
        this.addRentalItem(returnItem.getRentalItem());
        this.removeReturnItem(returnItem);
        return this;
    }

    private void calcalateLateFee(RentalItem rentalItem, LocalDate returnDate) {
        if(returnDate.compareTo(rentalItem.getOverdueDate())>0)
        {
            long point;
            point = Period.between(rentalItem.getOverdueDate(),returnDate).getDays()*10;
            LateFee addPoint = this.lateFee.addPoint(point);
            this.lateFee.setPoint(addPoint.getPoint());
        }
    }

    public RentalCard overdueItem(Item item)
    {
        RentalItem rentalItem = this.rentalItemList.stream().filter(i->i.getItem().equals(item)).findFirst().get();
        rentalItem.setOverdued(true);
        this.rentStatus = RentStatus.RENT_UNAVAILABLE;
        //억지코드임
        rentalItem.setOverdueDate(LocalDate.now().minusDays(1));
        return this;
    }

    public long makeAvailableRental(long point) throws Exception {
        if(this.rentalItemList.size() !=0) throw new IllegalArgumentException("모든 도서가 반납되어야 해제가 가능하다.");
        if(this.getLateFee().getPoint() != point)throw new IllegalStateException("해당 포인트로 연체를 해제할 수 없습니다.");

        this.setLateFee(lateFee. removePoint(point));
        if(this.getLateFee().getPoint() ==0){
            this.rentStatus =RentStatus.RENT_AVAILABLE;
        }
        return this.getLateFee().getPoint();
    }

    public long cancleMakeAvailableRental(long point) throws Exception {
        this.setLateFee(lateFee.addPoint(point));
        this.rentStatus = RentStatus.RENT_UNAVAILABLE;
        return this.lateFee.getPoint();
    }
}
