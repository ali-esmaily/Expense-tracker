package com.snapp.expensetracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snapp.expensetracker.entity.User;
import com.snapp.expensetracker.model.UserDto;
import com.snapp.expensetracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
 class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected ObjectMapper mapper;

    @Test
    void find_login_ok() throws Exception {
        User user = User.builder().password(passwordEncoder.encode("pass")).email("email1").build();
        userRepository.save(user);

        //when
        UserDto userDto = UserDto.builder().password("pass").email("email1").build();

        mockMvc
                .perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());
    }

    @Test
    void find_nologin_401() throws Exception {
        User user = User.builder().password(passwordEncoder.encode("pass")).email("email").build();
        userRepository.save(user);

        //when
        UserDto userDto = UserDto.builder().password("pass1").email("email").build();

        mockMvc
                .perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registerAndLogin() throws Exception {

        String token;

        //when
        UserDto userDto = UserDto.builder().password("pass2").email("email2").build();
        mockMvc
                .perform(
                        post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc
                .perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDto)))
                .andExpect(status().isOk()).andReturn();

        token = mvcResult.getResponse().getContentAsString();

        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.add("Authorization", String.join(" ", "Bearer", token));

        mockMvc
                .perform(get("/api/user").headers(httpHeaders))
                .andExpect(status().isOk());
    }

}
