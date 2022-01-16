package com.challenge.alkemy.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    
    private String jwt;
}
