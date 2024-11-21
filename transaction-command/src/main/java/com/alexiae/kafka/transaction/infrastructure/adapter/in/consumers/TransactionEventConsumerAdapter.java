package com.alexiae.kafka.transaction.infrastructure.adapter.in.consumers;

import com.alexiae.kafka.transaction.application.command.DepositTransactionResultCommand;
import com.alexiae.kafka.transaction.application.usecases.DepositTransactionService;
import com.alexiae.kafka.transaction.domain.event.DepositTransactionResultEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumerAdapter {

    @Autowired
    private DepositTransactionService depositTransactionService;


    @KafkaListener(topics = "transaction-deposit-result-topic", groupId = "customer-group-id",
            containerFactory = "transactionDepositResultKafkaListenerFactory")
    public void depositResult(DepositTransactionResultEvent event) {
        depositTransactionService.execute(DepositTransactionResultCommand.builder()
                .transactionId(event.getTransactionId())
                .status(event.getStatus())
                .build());
    }
}