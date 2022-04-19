package com.snapp.expensetracker.endpoint;

import com.snapp.expensetracker.model.AccountDto;
import com.snapp.expensetracker.model.CloseAccountDto;
import com.snapp.expensetracker.service.AccountService;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public Page<AccountDto> findAll(@ParameterObject Pageable pageable) {
        return accountService.findAll(pageable);
    }

    @GetMapping("holder/{holder}")
    public Page<AccountDto> findByHolder(@ParameterObject Pageable pageable, @PathVariable String holder) {
        return accountService.findAllByHolderLike(holder, pageable);
    }

    @GetMapping("{id}")
    public AccountDto findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping("open")
    public Long open(@RequestBody AccountDto accountDto) {
        return accountService.open(accountDto);
    }

    @PutMapping("close")
    public void close(@RequestBody CloseAccountDto accountDto) {
        accountService.close(accountDto.getAccountId());
    }
}
