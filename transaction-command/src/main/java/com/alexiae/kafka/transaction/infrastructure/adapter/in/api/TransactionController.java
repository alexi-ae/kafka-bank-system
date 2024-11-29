package com.alexiae.kafka.transaction.infrastructure.adapter.in.api;

import com.alexiae.kafka.transaction.domain.port.in.CreateTransactionUseCase;
import com.alexiae.kafka.transaction.application.dto.TransactionInitiatedRequest;
import com.alexiae.kafka.transaction.application.dto.TransactionInitiatedResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private CreateTransactionUseCase createTransactionService;

    @PostMapping("/deposit")
    public TransactionInitiatedResponse deposit(@RequestBody TransactionInitiatedRequest request,
                                                @RequestAttribute("customerId") String customerId) {
        return createTransactionService.init(request, Long.parseLong(customerId));
    }
}
