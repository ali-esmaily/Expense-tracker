package com.snapp.expensetracker.mapper;

import com.snapp.expensetracker.entity.Transaction;
import com.snapp.expensetracker.model.TransactionDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .amount(transaction.getAmount())
                .creationDate(transaction.getCreationDate())
                .description(transaction.getDescription())
                .expenseType(transaction.getExpenseType())
                .expenseFarsi(transaction.getExpenseType().getPerName())
                .side(transaction.getSide()).build();
    }


}
