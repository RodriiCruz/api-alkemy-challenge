package com.challenge.alkemy.api.auth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class AuthenticationRequest {
    
    private String username;
    private String password;
}
