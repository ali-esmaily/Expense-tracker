package com.snapp.expensetracker.entity;

import com.snapp.expensetracker.model.ExpenseType;
import com.snapp.expensetracker.model.Side;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    private BigDecimal amount;
    private Side side;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private ExpenseType expenseType;

}
