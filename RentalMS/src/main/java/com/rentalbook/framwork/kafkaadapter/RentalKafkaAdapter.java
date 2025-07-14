package com.rentalbook.framwork.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rentalbook.application.outputport.EventOutputPort;
import com.rentalbook.domain.model.event.ItemRented;
import com.rentalbook.domain.model.event.ItemReturned;
import com.rentalbook.domain.model.event.OverdueCleared;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class RentalKafkaAdapter implements EventOutputPort {

    @Value(value = "${producers.topic1.name}")
    private String TOPIC_RENT;
    @Value(value = "${producers.topic2.name}")
    private  String TOPIC_RETURN;
    @Value(value = "${producers.topic3.name}")
    private  String TOPIC_CLEAR;

    private final KafkaTemplate<String, ItemRented> kafkaTemplate1;
    private final KafkaTemplate<String, ItemReturned> kafkaTemplate2;
    private final KafkaTemplate<String, OverdueCleared> kafkaTemplate3;
    @Override
    public void occurRentalEvent(ItemRented itemRented) throws JsonProcessingException {
        CompletableFuture<SendResult<String, ItemRented>> future = kafkaTemplate1.send(TOPIC_RENT, itemRented);

        future.thenAccept(result -> {
            ItemRented g = result.getProducerRecord().value();
            log.info("Sent message=[{}] with offset=[{}]",
                    g.getItem().getNo(), result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Unable to send message=[{}] due to: {}",
                    itemRented.getItem().getNo(), ex.getMessage(), ex);
            return null;
        });
    }

    @Override
    public void occurReturnEvent(ItemReturned itemReturned) throws JsonProcessingException {
        CompletableFuture<SendResult<String, ItemReturned>> future =
                kafkaTemplate2.send(TOPIC_RETURN, itemReturned);

        future.thenAccept(result -> {
            ItemReturned g = result.getProducerRecord().value();
            log.info("Sent message=[{}] with offset=[{}]",
                    g.getItem().getNo(),
                    result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Unable to send message=[{}] due to: {}",
                    itemReturned.getItem().getNo(), ex.getMessage(), ex);
            return null;
        });
    }

    @Override
    public void occurOverdueClearedEvent(OverdueCleared overdueCleared) throws JsonProcessingException {
        CompletableFuture<SendResult<String, OverdueCleared>> future =
                kafkaTemplate3.send(TOPIC_CLEAR, overdueCleared);

        future.thenAccept(result -> {
            OverdueCleared g = result.getProducerRecord().value();
            log.info("Sent message=[{}] with offset=[{}]",
                    g.getIdName().getId(),
                    result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Unable to send message=[{}] due to: {}",
                    overdueCleared.getIdName().getId(), ex.getMessage(), ex);
            return null;
        });
    }
}
