package com.nisum.creacionusuarios;

import com.nisum.creacionusuarios.domain.User;
import com.nisum.creacionusuarios.domain.UserRequestDTO;
import com.nisum.creacionusuarios.domain.UserResponseDTO;
import com.nisum.creacionusuarios.repository.UserRepository;
import com.nisum.creacionusuarios.security.service.JwtService;
import com.nisum.creacionusuarios.service.UserService;
import com.nisum.creacionusuarios.service.ValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_exitoso() {

        UserRequestDTO requestDTO = new UserRequestDTO("Juan Pérez", "juan@example.com", "Password123", Collections.emptyList());

        when(userRepository.findByEmail(requestDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(requestDTO.getPassword())).thenReturn("hashedPassword");

        User usuarioMock = new User();
        usuarioMock.setId(UUID.randomUUID());
        usuarioMock.setPassword("Clavedeprueba.1234");
        usuarioMock.setEmail(requestDTO.getEmail());
        usuarioMock.setPhones(Collections.emptyList());
        usuarioMock.setCreated(LocalDateTime.now());
        usuarioMock.setName(requestDTO.getName());
        usuarioMock.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqdWFuQGV4YW1wbGUuY29tIiwiaWF0IjoxNjMwMzg3NjY1LCJleHBpcnkiOjE2MzAzODc2MjUsInJvbGUiOiJ1c3VhcmlvYXV0b3IiLCJpc3MiOiJodHRwczovL3d3dy5leGFtcGxlLmNvbS9hdXRoIn0.O7kp9zPql_xVbA6Af1JfFhCpWQHhG68cU5EGZayYY38");

        userService.setJwtService(jwtService);

        when(jwtService.generarToken(any(User.class))).thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqdWFuQGV4YW1wbGUuY29tIiwiaWF0IjoxNjMwMzg3NjY1LCJleHBpcnkiOjE2MzAzODc2MjUsInJvbGUiOiJ1c3VhcmlvYXV0b3IiLCJpc3MiOiJodHRwczovL3d3dy5leGFtcGxlLmNvbS9hdXRoIn0.O7kp9zPql_xVbA6Af1JfFhCpWQHhG68cU5EGZayYY38");

        UserResponseDTO response = userService.registrarUsuario(requestDTO);

        assertNotNull(response);
        assertEquals(requestDTO.getName(), response.getName());
        assertEquals(requestDTO.getEmail(), response.getEmail());
        assertNotNull(response.getCreated());
        assertNotNull(response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

}
