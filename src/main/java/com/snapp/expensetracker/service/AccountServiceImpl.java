package com.snapp.expensetracker.service;

import com.snapp.expensetracker.entity.Account;
import com.snapp.expensetracker.exception.AccountNotFoundException;
import com.snapp.expensetracker.mapper.AccountMapper;
import com.snapp.expensetracker.model.AccountDto;
import com.snapp.expensetracker.model.StateType;
import com.snapp.expensetracker.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Page<AccountDto> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(AccountMapper::toDto);
    }

    @Override
    public AccountDto findById(Long id) {
        return AccountMapper.toDto(accountRepository.findById(id).orElseThrow(AccountNotFoundException::new));
    }

    @Override
    public Page<AccountDto> findAllByHolderLike(String holder, Pageable pageable) {
        return accountRepository.findAllByHolderLike(holder, pageable).map(AccountMapper::toDto);
    }

    @Override
    public void open(AccountDto accountDto) {
        Account account = AccountMapper.toAccount(accountDto);
        account.setBalance(BigDecimal.ZERO);
        account.setStateType(StateType.OPEN);
        accountRepository.save(account);
    }

    public void close(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        account.setStateType(StateType.CLOSE);
        accountRepository.save(account);
    }


}
