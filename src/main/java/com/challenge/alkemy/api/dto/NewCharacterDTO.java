package com.challenge.alkemy.api.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class NewCharacterDTO {

    @NotBlank(message = "Debe ingresar la url de la imagen")
    private String image;
    
    @NotBlank(message = "Debe ingresar el nombre del personaje")
    private String name;
    
    @NotNull(message = "Debe ingresar la edad del personaje")
    private Integer age;
    
    @NotNull(message = "Debe ingresar el peso del personaje")
    private Double weight;
    
    @NotBlank(message = "Debe ingresar la historia del personaje")
    private String history;
    private List<Long> idMovies;
}
