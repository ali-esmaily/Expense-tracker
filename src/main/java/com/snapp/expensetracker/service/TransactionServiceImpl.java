package com.snapp.expensetracker.service;

import com.snapp.expensetracker.entity.Account;
import com.snapp.expensetracker.entity.Transaction;
import com.snapp.expensetracker.exception.AccountNotFoundException;
import com.snapp.expensetracker.mapper.TransactionMapper;
import com.snapp.expensetracker.model.ExpenseType;
import com.snapp.expensetracker.model.Side;
import com.snapp.expensetracker.model.TransactionDto;
import com.snapp.expensetracker.repository.AccountRepository;
import com.snapp.expensetracker.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public BigDecimal deposit(Long accountId, BigDecimal amount, ExpenseType expenseType) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Transaction deposit = Transaction.builder().account(account).amount(amount)
                .expenseType(expenseType)
                .description("deposit").side(Side.CREDIT).build();
        account.doTransaction(deposit);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return account.getBalance();
    }

    @Override
    public BigDecimal withdraw(Long accountId, BigDecimal amount, ExpenseType expenseType) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Transaction deposit = Transaction.builder().account(account).amount(amount)
                .expenseType(expenseType)
                .description("debit").side(Side.DEBIT).build();
        account.doTransaction(deposit);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        return account.getBalance();
    }

    public Page<TransactionDto> getAll(Long accountId, Pageable pageable) {
        return transactionRepository.findAllByAccountId(accountId, pageable).map(TransactionMapper::toDto);
    }

}
