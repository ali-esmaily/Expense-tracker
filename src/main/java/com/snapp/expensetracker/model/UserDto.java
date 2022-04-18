package com.snapp.expensetracker.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private String email;
    private String password;
}