package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@WebMvcTest(controllers = AuthController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void registerUser_shouldReturnRegisteredUser() throws Exception {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("rawpassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("newuser");
        savedUser.setPassword("encodedpassword");

        when(passwordEncoder.encode("rawpassword")).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"newuser\", \"password\":\"rawpassword\"}")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("newuser"))
                .andExpect(jsonPath("$.password").value("encodedpassword"));
    }
}
