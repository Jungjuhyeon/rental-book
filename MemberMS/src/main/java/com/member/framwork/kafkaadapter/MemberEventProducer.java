package com.member.framwork.kafkaadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.member.domain.event.EventResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberEventProducer {
    @Value(value = "${producers.topic1.name}")
    private String TOPIC;

    private final KafkaTemplate<String, EventResult> kafkaTemplate;

    public void occurEvent(EventResult result) throws JsonProcessingException {
        CompletableFuture<SendResult<String, EventResult>> future = kafkaTemplate.send(TOPIC, result);

        future.thenAccept(sendResult -> {
            EventResult sentEvent = sendResult.getProducerRecord().value();
            log.info("Sent message=[{}] with offset=[{}]", sentEvent.getEventType(), sendResult.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            // 보상 트랜잭션 또는 실패 처리
            log.error("Unable to send message=[{}] due to: {}", result.getEventType(), ex.getMessage(), ex);
            return null;
        });
    }
}
