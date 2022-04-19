package com.snapp.expensetracker.service;

import com.snapp.expensetracker.model.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AccountService {

    Page<AccountDto> findAll(Pageable pageable);
    AccountDto findById(Long aLong);
    Page<AccountDto> findAllByHolderLike(String holder, Pageable pageable);
    Long open(AccountDto accountDto);
    void close(Long id);

}
