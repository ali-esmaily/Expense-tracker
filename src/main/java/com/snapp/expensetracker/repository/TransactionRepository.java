package com.snapp.expensetracker.repository;

import com.snapp.expensetracker.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("from Transaction t where t.account.id = :id ")
    Page<Transaction> findAllByAccountId(Long id, Pageable pageable);

}
