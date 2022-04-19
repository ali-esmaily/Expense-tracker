package com.snapp.expensetracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.expensetracker.entity.Account;
import com.snapp.expensetracker.model.AccountDto;
import com.snapp.expensetracker.model.AccountType;
import com.snapp.expensetracker.repository.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void openAccount() throws Exception {
        AccountDto accountDto = AccountDto.builder().accountType(AccountType.CURRENT).holder("Tester").build();
        mockMvc
                .perform(
                        post("/api/accounts/open")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(accountDto)))
                .andExpect(status().isOk());

        Assertions.assertThat(accountRepository.findAll().get(0).getHolder())
                .isEqualTo("Tester");
    }

    @Test
    void closeAccount() throws Exception {

        Account account = Account.builder().accountType(AccountType.CURRENT).holder("Tester").build();
        accountRepository.save(account);

        mockMvc
                .perform(get("/api/accounts/close/1"))
                .andExpect(status().isOk());

        Assertions.assertThat(accountRepository.findAll().get(0).getStateType().name())
                .isEqualTo("CLOSE");
    }


    @Test
    void findById() throws Exception {

        Account account = Account.builder().accountType(AccountType.CURRENT).holder("Tester").build();
        accountRepository.save(account);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/accounts/1"))
                .andExpect(status().isOk()).andReturn();

        Assertions.assertThat(mvcResult.getResponse().getContentAsString())
                .contains("Tester");
    }

    @Test
    void findByHolder() throws Exception {

        Account account = Account.builder().accountType(AccountType.CURRENT).holder("Tester").build();
        accountRepository.save(account);

        MvcResult mvcResult = mockMvc
                .perform(get("/api/accounts/holder/Tester"))
                .andExpect(status().isOk()).andReturn();

        Assertions.assertThat(mvcResult.getResponse().getContentAsString())
                .contains("Tester");
    }

}
