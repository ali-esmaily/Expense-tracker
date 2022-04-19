package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    Page<Account> findAll(Pageable pageable);

    @Override
    Optional<Account> findById(Long id);

    Page<Account> findAllByHolderLike(String holder, Pageable pageable);

}
