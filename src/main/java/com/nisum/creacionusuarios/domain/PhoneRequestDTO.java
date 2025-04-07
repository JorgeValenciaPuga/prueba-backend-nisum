package com.nisum.creacionusuarios.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PhoneRequestDTO {

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String number;

    @NotBlank(message = "El código de área es obligatorio")
    @Pattern(regexp = "^\\d+$", message = "El código de área solo debe contener números")
    private String citycode;

    @NotBlank(message = "El código de país es obligatorio")
    @Pattern(regexp = "^\\d+$", message = "El código de país solo debe contener números")
    private String countrycode;


    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getCitycode() { return citycode; }
    public void setCitycode(String citycode) { this.citycode = citycode; }

    public String getCountrycode() { return countrycode; }
    public void setCountrycode(String countrycode) { this.countrycode = countrycode; }
}
