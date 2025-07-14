package com.book.framwork.kafkaadapter;

import com.book.domain.model.event.EventResult;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class BookEventProducer {

    @Value(value = "${producers.topic1.name}")
    private String TOPIC;
    private final KafkaTemplate<String, EventResult> kafkaTemplate;

    public void occurEvent(EventResult result) throws JsonProcessingException {

        CompletableFuture<SendResult<String, EventResult>> future = kafkaTemplate.send(TOPIC, result);

        future.thenAccept(sendResult -> {
            EventResult sentResult = sendResult.getProducerRecord().value();
            log.info("Sent message=[{}] with offset=[{}]",
                    sentResult.getItem().getNo(), sendResult.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Unable to send message=[{}] due to: {}",
                    result.getItem().getNo(), ex.getMessage(), ex);
            return null;
        });
    }

}
