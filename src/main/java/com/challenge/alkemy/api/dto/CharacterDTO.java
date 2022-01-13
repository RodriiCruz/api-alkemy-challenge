package com.challenge.alkemy.api.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class CharacterDTO {

    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<GetMovieDTO> movies;
}
