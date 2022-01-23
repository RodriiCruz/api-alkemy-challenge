package com.challenge.alkemy.api.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class AuthenticationRequest {

    @Email(message = "Debe ingresar un email válido")
    private String username;

    @NotBlank(message = "La contraseña no puede ser nula ni estar vacía")
    private String password;
}
