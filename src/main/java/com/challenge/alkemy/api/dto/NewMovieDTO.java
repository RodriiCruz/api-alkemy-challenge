package com.challenge.alkemy.api.dto;

import java.util.Date;
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
public class NewMovieDTO {

    private String image;
    
    @NotBlank(message = "Debe ingresar el titulo")
    private String title;
    
    @NotNull(message = "Debe ingresar la fecha de estreno")
    private Date creationDate;
    
    @NotNull(message = "Debe ingresar la calificacion")
    private Integer qualification;
    
    @NotNull(message = "Debe ingresar el id del genero")
    private Long idGenre;
    
    private List<Long> characters;
}
