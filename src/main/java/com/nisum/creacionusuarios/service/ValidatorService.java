package com.nisum.creacionusuarios.service;

import com.nisum.creacionusuarios.domain.User;
import com.nisum.creacionusuarios.exception.UserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidatorService {

    @Value("${emailregex}")
    private String emailregex;
    @Value("${passwordregex}")
    private String passwordregex;

    public void validarCorreo(String correo) {
        if (!correo.matches(emailregex)) {
            throw new UserException("El correo no tiene un formato válido.");
        }
    }

    public void validarClave(String clave) {
        if (!clave.matches(passwordregex)) {
            throw new UserException("La clave debe tener al menos una mayúscula, una minúscula y dos números.");
        }
    }

    public void validarCorreoExistente(Optional<User> usuarioExistente){
        if (usuarioExistente.isPresent()) {
            throw new UserException("El correo ya está registrado");
        }
    }
}
