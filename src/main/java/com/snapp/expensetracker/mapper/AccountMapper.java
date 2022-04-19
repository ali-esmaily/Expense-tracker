package com.snapp.expensetracker.mapper;

import com.snapp.expensetracker.entity.Account;
import com.snapp.expensetracker.model.AccountDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper {

    public static AccountDto toDto(Account account) {
       return AccountDto.builder().accountType(account.getAccountType()).id(account.getId())
               .stateType(account.getStateType()).balance(account.getBalance())
               .creationDate(account.getCreationDate()).holder(account.getHolder()).build();
    }

    public static Account toAccount(AccountDto accountDto) {
        return Account.builder().accountType(accountDto.getAccountType())
                .balance(accountDto.getBalance())
                .holder(accountDto.getHolder()).build();
    }

}
