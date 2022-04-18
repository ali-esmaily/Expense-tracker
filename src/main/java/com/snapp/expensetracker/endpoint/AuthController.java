package com.snapp.expensetracker.endpoint;

import com.snapp.expensetracker.model.UserDto;
import com.snapp.expensetracker.security.JWTUtil;
import com.snapp.expensetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto userDto) {
        userService.register(userDto);
        return jwtUtil.generateToken(userDto.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto body) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword()));
            return ResponseEntity.ok(jwtUtil.generateToken(body.getEmail()));
        } catch (AuthenticationException authExc) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}