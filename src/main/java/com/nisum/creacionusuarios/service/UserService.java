package com.nisum.creacionusuarios.service;

import com.nisum.creacionusuarios.domain.Phone;
import com.nisum.creacionusuarios.domain.User;
import com.nisum.creacionusuarios.domain.UserRequestDTO;
import com.nisum.creacionusuarios.domain.UserResponseDTO;
import com.nisum.creacionusuarios.repository.UserRepository;
import com.nisum.creacionusuarios.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ValidatorService validatorService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, ValidatorService validatorService) {
        this.userRepository = userRepository;
        this.validatorService = validatorService;
    }

    @Transactional
    public UserResponseDTO registrarUsuario(UserRequestDTO request) {

        try{
            Optional<User> usuarioExistente = userRepository.findByEmail(request.getEmail());

            validatorService.validarCorreoExistente(usuarioExistente);

            validatorService.validarCorreo(request.getEmail());

            validatorService.validarClave(request.getPassword());

            String claveEncriptada = passwordEncoder.encode(request.getPassword());

            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(claveEncriptada);
            user.setCreated(LocalDateTime.now());

            List<Phone> telefonos = request.getPhones().stream()
                    .map(t -> new Phone(t.getNumber(), t.getCitycode(), t.getCountrycode(), user))
                    .toList();
            user.setPhones(telefonos);

            String token = jwtService.generarToken(user);

            user.setToken(token);

            userRepository.save(user);

            return new UserResponseDTO(user.getId(),user.getName(),user.getEmail(),user.getCreated(),LocalDateTime.now(),LocalDateTime.now(),token,true);
        }catch(Exception e){
            throw e;
        }
        
    }
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}