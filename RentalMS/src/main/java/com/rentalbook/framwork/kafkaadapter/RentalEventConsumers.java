package com.rentalbook.framwork.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentalbook.application.usecase.CompensationUsecase;
import com.rentalbook.domain.model.event.EventResult;
import com.rentalbook.domain.model.event.EventType;
import com.rentalbook.domain.model.vo.IdName;
import com.rentalbook.domain.model.vo.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentalEventConsumers {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CompensationUsecase compensationUsecase;

    @KafkaListener(topics="${consumer.topic1.name}",groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws Exception {

        try {
            System.out.printf("ConsumerRecord: " + record.value());
            EventResult eventResult = objectMapper.readValue(record.value(), EventResult.class);

            IdName idName = eventResult.getIdName();
            Item item = eventResult.getItem();
            long point = eventResult.getPoint();

            if (!eventResult.isSuccessed()) {
                EventType eventType = eventResult.getEventType();
                System.out.println("eventType =" + eventType.toString());
                switch (eventType) {
                    case RENT:
                        compensationUsecase.cancleRentItem(idName, item);
                        log.info("대여취소 보상트랜젝션 실행");
                        break;
                    case RETURN:
                        compensationUsecase.cancleReturnItem(idName, item, point);
                        log.info("반납취소 보상트랜젝션 실행");
                        break;
                    case OVERDUE:
                        compensationUsecase.cancleMakeAvailableRental(idName, point);
                        log.info("연체해제처리취소 보상트랜젝션 실행");
                        break;
                    default:
                        // 다른 이벤트 유형에 대한 처리를 여기에 추가
                }
                // 포인트 보상처리 (모든 경우에 공통)
            } else {
                System.out.println("eventResult.isSuccessed()" + eventResult.isSuccessed());
            }
        } catch (Exception e) {
            throw e; // 예외에 대한 처리
        }
    }
}