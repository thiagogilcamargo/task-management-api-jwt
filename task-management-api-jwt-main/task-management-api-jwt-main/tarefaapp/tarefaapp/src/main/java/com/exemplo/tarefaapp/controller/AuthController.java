package com.exemplo.tarefaapp.controller;

import com.exemplo.tarefaapp.dto.AuthenticationDto;
import com.exemplo.tarefaapp.dto.RegisterDto;
import com.exemplo.tarefaapp.exception.CustomException;
import com.exemplo.tarefaapp.model.User;
import com.exemplo.tarefaapp.repository.UserRepository;
import com.exemplo.tarefaapp.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Registrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já em uso")
    })
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterDto registerDto) {
        if (userRepository.findByEmail(registerDto.email()).isPresent()) {
            throw new CustomException("Email já está em uso.");
        }
        User user = new User();
        user.setEmail(registerDto.email());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        userRepository.save(user);
    }

    @Operation(summary = "Login do usuário e obtenção do token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthenticationDto authenticationDto) {
        User user = userRepository.findByEmail(authenticationDto.username())
                .orElseThrow(() -> new CustomException("Usuário não encontrado com o email fornecido."));
        if (passwordEncoder.matches(authenticationDto.password(), user.getPassword())) {
            return jwtTokenProvider.createToken(user.getEmail());
        }
        throw new CustomException("Credenciais inválidas.");
    }
}
