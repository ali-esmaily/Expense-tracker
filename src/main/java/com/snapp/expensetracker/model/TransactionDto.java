package com.snapp.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class TransactionDto {

    private String id;
    private LocalDateTime creationDate;
    private BigDecimal amount;
    private Side side;
    private String description;
    private ExpenseType expenseType;
    private String expenseFarsi;
}
