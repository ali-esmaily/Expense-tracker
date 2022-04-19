package com.snapp.expensetracker.entity;


import com.snapp.expensetracker.model.AccountType;
import com.snapp.expensetracker.model.StateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String holder;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    private AccountType accountType;
    private StateType stateType;
    private BigDecimal balance;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactions = new ArrayList<>();

    public void doTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }

}
