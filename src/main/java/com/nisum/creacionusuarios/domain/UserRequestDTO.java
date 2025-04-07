package com.nisum.creacionusuarios.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserRequestDTO {

    public UserRequestDTO(String name, String email, String password, List<PhoneRequestDTO> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d).+$",
            message = "La clave debe contener al menos una letra mayúscula, una minúscula y dos números")
    private String password;

    private List<PhoneRequestDTO> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneRequestDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneRequestDTO> phones) {
        this.phones = phones;
    }
}

