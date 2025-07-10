package com.rentalbook.framwork.jpaadapter;


import com.rentalbook.domain.model.RentalCard;
import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.RentalCardNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalCardRepository extends JpaRepository<RentalCard, RentalCardNo> {

    @Query("select m from RentalCard m where m.member.id = :id")
    Optional<RentalCard> findByMemberId(@Param("id") String memberID);

    Optional<RentalCard> findByMember(IdName member);

    @Query("select m from RentalCard m where m.rentalCardNo.no = :id")
    Optional<RentalCard> findById(@Param("id") Long rentalCardId);
}
