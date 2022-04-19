package com.snapp.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class TransactionOperatorDto {
    private Long accountId;
    private BigDecimal amount;
    private ExpenseType expenseType;
}
