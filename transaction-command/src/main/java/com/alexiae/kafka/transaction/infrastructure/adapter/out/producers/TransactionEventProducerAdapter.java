package com.alexiae.kafka.transaction.infrastructure.adapter.out.producers;

import com.alexiae.kafka.transaction.application.port.out.TransactionEventProducer;
import com.alexiae.kafka.transaction.domain.event.DepositTransactionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventProducerAdapter implements TransactionEventProducer {

    @Value("${spring.kafka.producer.topic.transaction-deposit}")
    private String transactionDepositTopic;

    @Autowired
    private KafkaTemplate<String, DepositTransactionEvent> depositTransactionEventKafkaTemplate;

    @Override
    public void publishTransactionDepositEvent(DepositTransactionEvent event) {
        depositTransactionEventKafkaTemplate.send(transactionDepositTopic, event);
    }
}
