package com.exemplo.tarefaapp;

import com.exemplo.tarefaapp.model.User;
import com.exemplo.tarefaapp.repository.UserRepository;
import com.exemplo.tarefaapp.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setUp() {
        // Limpar banco de dados e criar um usuário de teste
        userRepository.deleteAll();

        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        user.setName("Test User");
        userRepository.save(user);
    }

    @Test
    void testLogin() throws Exception {
        String jsonRequest = "{ \"email\": \"user@example.com\", \"password\": \"password\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists()); // Verifica se o token está presente na resposta
    }

    @Test
    void testRegister() throws Exception {
        String jsonRequest = "{ \"email\": \"newuser@example.com\", \"password\": \"newpassword\", \"name\": \"New User\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user@example.com", roles = {"USER"})
    void testCreateTask() throws Exception {
        String jsonRequest = "{ \"title\": \"New Task\", \"description\": \"Task Description\", \"dueDate\": \"2024-12-31\", \"status\": \"Pendente\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Task"));
    }

    @Test
    @WithMockUser(username = "user@example.com", roles = {"USER"})
    void testGetTasks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
