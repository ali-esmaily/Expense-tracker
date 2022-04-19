package com.snapp.expensetracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.expensetracker.entity.Account;
import com.snapp.expensetracker.model.AccountType;
import com.snapp.expensetracker.model.TransactionOperatorDto;
import com.snapp.expensetracker.repository.AccountRepository;
import com.snapp.expensetracker.repository.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Test
    void depositTransaction() throws Exception {


        Account account = Account.builder().balance(BigDecimal.ZERO).accountType(AccountType.CURRENT)
                .holder("Tester").build();
        accountRepository.save(account);

        TransactionOperatorDto operatorDto = TransactionOperatorDto.builder().accountId(1L)
                .amount(new BigDecimal(100)).build();

        mockMvc
                .perform(
                        post("/api/transactions/deposit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(operatorDto)))
                .andExpect(status().isOk());

        Assertions.assertThat(accountRepository.findById(1L).get().getBalance().longValue())
                .isEqualTo(100L);

        Assertions.assertThat(transactionRepository.findAllByAccountId(1L, Pageable.unpaged()).getTotalPages())
                .isEqualTo(1L);
    }

    @Test
    void creditTransaction() throws Exception {

        accountRepository.deleteAll();
        transactionRepository.deleteAll();

        Account account = Account.builder().balance(new BigDecimal(100)).accountType(AccountType.CURRENT)
                .holder("Tester").build();
        accountRepository.save(account);

        TransactionOperatorDto operatorDto = TransactionOperatorDto.builder().accountId(1L)
                .amount(new BigDecimal(100)).build();

        mockMvc
                .perform(
                        post("/api/transactions/withdraw")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(operatorDto)))
                .andExpect(status().isOk());

        Assertions.assertThat(accountRepository.findById(1L).get().getBalance().longValue())
                .isZero();

        Assertions.assertThat(transactionRepository.findAllByAccountId(1L, Pageable.unpaged()).getTotalPages())
                .isEqualTo(1L);
    }


}
