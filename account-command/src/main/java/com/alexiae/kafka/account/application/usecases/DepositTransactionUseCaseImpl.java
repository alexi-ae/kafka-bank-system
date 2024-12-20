package com.alexiae.kafka.account.application.usecases;

import com.alexiae.kafka.account.domain.command.DepositTransactionCommand;
import com.alexiae.kafka.account.application.port.out.TransactionEventProducer;
import com.alexiae.kafka.account.domain.port.in.DepositTransactionUseCase;
import com.alexiae.kafka.account.domain.enums.AccountStatus;
import com.alexiae.kafka.account.domain.event.DepositTransactionResultEvent;
import com.alexiae.kafka.account.domain.exception.ApiRestException;
import com.alexiae.kafka.account.domain.exception.ErrorReason;
import com.alexiae.kafka.account.domain.exception.ErrorSource;
import com.alexiae.kafka.account.domain.model.Account;
import com.alexiae.kafka.account.domain.port.out.AccountPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositTransactionUseCaseImpl implements DepositTransactionUseCase {

    @Autowired
    private AccountPersistencePort accountPersistencePort;

    @Autowired
    private TransactionEventProducer transactionEventProducer;


    @Override
    public void execute(DepositTransactionCommand command) {

        try {
            Account originAccount = accountPersistencePort.getById(command.getOriginAccountId());

            validateOriginAccount(originAccount, command);

            Account destinyAccount = accountPersistencePort.getById(command.getDestinationAccountId());

            validateDestinyAccount(destinyAccount, command);

            // procesar transferencia

            originAccount.setCurrentBalance(originAccount.getCurrentBalance().subtract(command.getAmount()));
            accountPersistencePort.update(originAccount);
            destinyAccount.setCurrentBalance(destinyAccount.getCurrentBalance().add(command.getAmount()));
            accountPersistencePort.update(destinyAccount);
            // responder kafka ok
            transactionEventProducer.publishTransactionDepositResultEvent(DepositTransactionResultEvent.builder()
                    .transactionId(command.getTransactionId())
                    .status("DEPOSITED")
                    .build());
        } catch (Exception e) {
            // responder kafka con error - compensacion
            transactionEventProducer.publishTransactionDepositResultEvent(DepositTransactionResultEvent.builder()
                    .transactionId(command.getTransactionId())
                    .status("CANCELLED")
                    .build());
        }
    }

    private void validateDestinyAccount(Account destinyAccount, DepositTransactionCommand command) {
        if (Boolean.FALSE.equals(AccountStatus.ACTIVE.equals(destinyAccount.getStatus()))) {
            //cuenta no esta activa
            throw ApiRestException.builder()
                    .reason(ErrorReason.BAD_REQUEST)
                    .source(ErrorSource.BUSINESS_SERVICE)
                    .build();
        }

        if (command.getCustomerId().equals(destinyAccount.getCustomerId())) {
            // transferencia entre mis propias cuentas
        }
    }

    private void validateOriginAccount(Account originAccount, DepositTransactionCommand command) {
        if (Boolean.FALSE.equals(AccountStatus.ACTIVE.equals(originAccount.getStatus()))) {
            //cuenta no esta activa
            throw ApiRestException.builder()
                    .reason(ErrorReason.BAD_REQUEST)
                    .source(ErrorSource.BUSINESS_SERVICE)
                    .build();
        }

        if (originAccount.getCustomerId() != command.getCustomerId()) {
            // cuenta origen no del usuario en sesion
            throw ApiRestException.builder()
                    .reason(ErrorReason.BAD_REQUEST)
                    .source(ErrorSource.BUSINESS_SERVICE)
                    .build();
        }

        if (originAccount.getCurrentBalance().compareTo(command.getAmount()) <= 0) {
            // el monto de la cuenta origen es inferior al monto a transaccionar
            throw ApiRestException.builder()
                    .reason(ErrorReason.BAD_REQUEST)
                    .source(ErrorSource.BUSINESS_SERVICE)
                    .build();
        }
    }
}
