package com.challenge.alkemy.api.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class GenreDTO {

    private Long id;
    
    @NotBlank(message = "Debe ingresar el nombre del género")
    private String name;
    
    @NotBlank(message = "Debe ingresar la url de la imagen")
    private String image;
}
