package com.alexiae.kafka.account.infrastructure.adapter.out.producers;

import com.alexiae.kafka.account.application.port.out.TransactionEventProducer;
import com.alexiae.kafka.account.domain.event.DepositTransactionResultEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventProducerAdapter implements TransactionEventProducer {

    @Value("${spring.kafka.producer.topic.transaction-deposit-result}")
    private String transactionDepositResultTopic;

    @Autowired
    private KafkaTemplate<String, DepositTransactionResultEvent> depositTransactionResultEventKafkaTemplate;

    @Override
    public void publishTransactionDepositResultEvent(DepositTransactionResultEvent event) {
        depositTransactionResultEventKafkaTemplate.send(transactionDepositResultTopic, event);
    }
}
