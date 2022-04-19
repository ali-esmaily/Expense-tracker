package com.snapp.expensetracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 0, max = 20)
    private String description;
    private ExpenseType expenseType;
    private String expenseFarsi;
}
