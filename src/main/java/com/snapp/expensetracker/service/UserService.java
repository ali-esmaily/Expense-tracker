package com.snapp.expensetracker.service;


import com.snapp.expensetracker.model.UserDto;

public interface UserService {
    void register(UserDto userDto);
    UserDto findByEmail(String email);
}
