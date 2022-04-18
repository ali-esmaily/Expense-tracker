package com.snapp.expensetracker.service;

import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.model.UserDto;
import com.snapp.expensetracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDto userDto) {
        User user = User.builder().email(userDto.getEmail()).password(passwordEncoder.encode(userDto.getPassword())).build();
        userRepository.save(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return UserDto.builder().email(user.getEmail()).build();
    }
}
