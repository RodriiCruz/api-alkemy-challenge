package com.challenge.alkemy.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class MovieDTO {

    private Long id;
    private String image;
    private String title;
    private Date creationDate;
    private Integer qualification;
    private GenreDTO genre;
    @JsonIgnore
    private Long idGenre;
    private List<GetCharacterDTO> characters;
}
