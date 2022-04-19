package com.snapp.expensetracker.service;

import com.snapp.expensetracker.model.ExpenseType;
import com.snapp.expensetracker.model.TransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface TransactionService {
    BigDecimal deposit(Long accountId, BigDecimal amount, ExpenseType expenseType);
    BigDecimal withdraw(Long accountId, BigDecimal amount, ExpenseType expenseType);
    Page<TransactionDto> getAll(Long accountId, Pageable pageable);
}
