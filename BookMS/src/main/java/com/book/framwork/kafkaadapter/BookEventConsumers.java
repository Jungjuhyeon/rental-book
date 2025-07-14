package com.book.framwork.kafkaadapter;

import com.book.application.usecase.MakeAvailableUsecase;
import com.book.application.usecase.MakeUnAvailableUsecase;
import com.book.domain.model.event.ItemRented;
import com.book.domain.model.event.ItemReturned;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookEventConsumers {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MakeAvailableUsecase makeAvailableUsecase;
    private final MakeUnAvailableUsecase makeUnavailable;

    @KafkaListener(topics = "${consumer.topic1.name}", groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_rent:" + record.value());

        ItemRented itemRented = objectMapper.readValue(record.value(), ItemRented.class);
        makeUnavailable.unavailable(Long.valueOf(itemRented.getItem().getNo()));
    }

    @KafkaListener(topics = "${consumer.topic2.name}", groupId = "${consumer.groupid.name}")
    public void consumeReturn(ConsumerRecord<String, String> record) throws IOException {
        System.out.printf("rental_return:" + record.value());

        ItemReturned itemReturned = objectMapper.readValue(record.value(), ItemReturned.class);
        makeAvailableUsecase.available(Long.valueOf(itemReturned.getItem().getNo()));
    }
}