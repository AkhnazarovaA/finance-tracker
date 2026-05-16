package com.ainura.finance_tracker.kafka.controller;

import com.ainura.finance_tracker.kafka.dto.TransactionCreatedEvent;
import com.ainura.finance_tracker.kafka.service.TransactionEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaTestController {

    private final TransactionEventProducer transactionEventProducer;

    @PostMapping("/test")
    public String sendTransactionCreated() {
        TransactionCreatedEvent event = new TransactionCreatedEvent(
                1L,
                1L,
                "EXPENSE",
                new BigDecimal("1400.00"),
                "COFFEE",
                LocalDate.now()
        );
        transactionEventProducer.sendTransactionCreated(event);
        return "Message sent";
    }
}
