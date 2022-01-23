package com.challenge.alkemy.api.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Rodrigo Cruz
 */
@Data
public class UserDTO {

    @Email(message = "Debe ingresar un email válido")
    @NotBlank(message = "El mail no puede ser nulo ni estar vacío")
    private String username;
    
    @Size(min = 8, message = "La contraseña debe tener, al menos, 8 caracteres")
    @NotBlank(message = "La contraseña no puede ser nula ni estar vacía")
    private String password;
}