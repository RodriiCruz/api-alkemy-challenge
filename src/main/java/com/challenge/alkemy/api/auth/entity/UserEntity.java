package com.challenge.alkemy.api.auth.entity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class UserEntity {

    private Long id;
    private String username;
    private String password;
}
