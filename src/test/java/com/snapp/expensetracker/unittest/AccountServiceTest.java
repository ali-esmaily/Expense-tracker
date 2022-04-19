package com.snapp.expensetracker.unittest;

import com.snapp.expensetracker.entity.Account;
import com.snapp.expensetracker.model.AccountDto;
import com.snapp.expensetracker.model.AccountType;
import com.snapp.expensetracker.model.StateType;
import com.snapp.expensetracker.repository.AccountRepository;
import com.snapp.expensetracker.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void givenAccountDto_whenTryingToOPenAccount_whenSaveHit() {
        AccountDto accountDto = AccountDto.builder().accountType(AccountType.CURRENT).holder("Tester").build();
        accountService.open(accountDto);

        Account account = Account.builder().balance(BigDecimal.ZERO)
                .stateType(StateType.OPEN).accountType(AccountType.CURRENT).holder("Tester")
                .build();

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void givenAccountId_whenTryingToCloseAccount_whenSaveHit() {

        Account account = Account.builder().balance(BigDecimal.ZERO)
                .stateType(StateType.OPEN).accountType(AccountType.CURRENT).holder("Tester")
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(account));

        accountService.close(1L);

        account.setStateType(StateType.CLOSE);

        verify(accountRepository, times(1)).save(account);
    }

}
