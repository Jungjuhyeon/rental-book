package com.member.framwork.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.application.usecase.SavePointUsecase;
import com.member.application.usecase.UsePointUsecase;
import com.member.domain.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberEventConsumers {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SavePointUsecase savePointUsecase;
    private final UsePointUsecase usePointUsecase;
    private final MemberEventProducer eventProducer;

    @KafkaListener(topics="${consumer.topic1.name}",groupId = "${consumer.groupid.name}")
    public void consumeRent(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_rent:"+ record.value());
        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        savePointUsecase.savePoint(itemRented.getIdName(),itemRented.getPoint());
    }

    @KafkaListener(topics="${consumer.topic2.name}",groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException{
        System.out.printf("rental_return:"+ record.value());
        ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);
        savePointUsecase.savePoint(itemReturned.getIdName(),itemReturned.getPoint());
    }

    @KafkaListener(topics="${consumer.topic3.name}",groupId = "${consumer.groupid.name}")
    public void consumeClear(ConsumerRecord<String, String> record) throws Exception {
        OverdueCleared overdueCleared = objectMapper.readValue(record.value(), OverdueCleared.class);

        EventResult eventResult = new EventResult();
        eventResult.setEventType(EventType.OVERDUE);
        eventResult.setIdName(overdueCleared.getIdName());
        eventResult.setPoint(overdueCleared.getPoint());

        System.out.printf(record.value());
        try
        {
            usePointUsecase.userPoint(overdueCleared.getIdName(),overdueCleared.getPoint());
            eventResult.success();
        }
        catch (Exception e) {
            eventResult.fail();
        }

        eventProducer.occurEvent(eventResult);
    }

    @KafkaListener(topics="${consumer.topic4.name}",groupId = "${consumer.groupid.name}")
    public void consumeUsePoint(ConsumerRecord<String, String> record) throws Exception {
        System.out.printf(record.value());
        PointUseCommand pointUseCommand = objectMapper.readValue(record.value(), PointUseCommand.class);
        usePointUsecase.userPoint(pointUseCommand.getIdName(),pointUseCommand.getPoint());
    }
}