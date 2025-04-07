package com.nisum.creacionusuarios.controller;

import com.nisum.creacionusuarios.domain.UserRequestDTO;
import com.nisum.creacionusuarios.domain.UserResponseDTO;
import com.nisum.creacionusuarios.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UserRequestDTO request) {
        try {
            UserResponseDTO usuarioCreado = userService.registrarUsuario(request);
            return ResponseEntity.status(201).body(usuarioCreado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

