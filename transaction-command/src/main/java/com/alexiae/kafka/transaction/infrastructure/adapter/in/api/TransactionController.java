package com.alexiae.kafka.transaction.infrastructure.adapter.in.api;

import com.alexiae.kafka.transaction.application.usecases.CreateTransactionService;
import com.alexiae.kafka.transaction.domain.dto.TransactionInitiatedRequest;
import com.alexiae.kafka.transaction.domain.dto.TransactionInitiatedResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private CreateTransactionService createTransactionService;

    @PostMapping("/init")
    public TransactionInitiatedResponse init(@RequestBody TransactionInitiatedRequest request,
                                                @RequestAttribute("customerId") String customerId) {
        return createTransactionService.init(request, Long.parseLong(customerId));
    }
}
