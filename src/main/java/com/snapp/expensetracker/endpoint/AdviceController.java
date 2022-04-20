package com.snapp.expensetracker.endpoint;

import com.snapp.expensetracker.exception.AccountNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> exception(AccountNotFoundException exception) {
        ErrorMessage message = new ErrorMessage("account not found");
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


}
