package com.challenge.alkemy.api.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Rodrigo Cruz
 */
@Data
public class UserDTO {

    @Email(message = "Debe ingresar un email")
    private String username;
    @Size(min = 8)
    private String password;
}