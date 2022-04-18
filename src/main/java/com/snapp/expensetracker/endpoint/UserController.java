package com.snapp.expensetracker.endpoint;

import com.snapp.expensetracker.model.UserDto;
import com.snapp.expensetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserDto getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByEmail(email);
    }

}