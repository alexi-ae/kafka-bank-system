package com.alexiae.kafka.account.infrastructure.adapter.in.consumers;

import com.alexiae.kafka.account.application.command.DepositTransactionCommand;
import com.alexiae.kafka.account.application.usecases.DepositTransactionService;
import com.alexiae.kafka.account.domain.event.DepositTransactionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumerAdapter {

    @Autowired
    private DepositTransactionService depositTransactionService;

    @KafkaListener(topics = "transaction-deposit-topic", groupId = "customer-group-id",
            containerFactory = "transactionDepositKafkaListenerFactory")
    public void deposit(DepositTransactionEvent event) {
        depositTransactionService.execute(DepositTransactionCommand.builder()
                .originAccountId(event.getOriginAccountId())
                .destinationAccountId(event.getDestinationAccountId())
                .customerId(event.getCustomerId())
                .amount(event.getAmount())
                .build());
    }
}
