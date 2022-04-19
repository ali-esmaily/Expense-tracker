package com.snapp.expensetracker.model;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {
    @Hidden
    private Long id;
    private String holder;
    @Hidden
    private LocalDateTime creationDate;
    private AccountType accountType;
    @Hidden
    private BigDecimal balance;
    @Hidden
    private StateType stateType;
}
