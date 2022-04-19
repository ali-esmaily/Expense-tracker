package com.snapp.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {
    private String holder;
    private LocalDateTime creationDate;
    private AccountType accountType;
    private BigDecimal balance;
    private StateType stateType;
}
