package com.ainura.finance_tracker.kafka.service;

import com.ainura.finance_tracker.kafka.dto.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionEventProducer {
    private final KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate;

    public void sendTransactionCreated(TransactionCreatedEvent event) {
        kafkaTemplate.send("transactions.created", event.userId().toString(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.out.println("Kafka send failed: " + ex.getMessage());
                    } else {
                        System.out.println("Kafka sent: " + result.getRecordMetadata());
                    }
                });
    }
}
