package com.snapp.expensetracker.endpoint;

import com.snapp.expensetracker.model.TransactionDto;
import com.snapp.expensetracker.model.TransactionOperatorDto;
import com.snapp.expensetracker.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("deposit")
    public BigDecimal deposit(@RequestBody TransactionOperatorDto request) {
        return transactionService.deposit(request.getAccountId(), request.getAmount(), request.getExpenseType());
    }

    @PostMapping("withdraw")
    public BigDecimal withdraw(@RequestBody TransactionOperatorDto request) {
        return transactionService.withdraw(request.getAccountId(), request.getAmount(), request.getExpenseType());
    }

    @GetMapping("{accountId}")
    public Page<TransactionDto> findAll(@PathVariable Long accountId, Pageable pageable) {
        return transactionService.getAll(accountId, pageable);
    }

}
