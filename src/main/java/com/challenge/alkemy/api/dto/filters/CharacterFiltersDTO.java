package com.challenge.alkemy.api.dto.filters;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class CharacterFiltersDTO {

    private String name;
    private Integer age;
    private Double weight;
    private Set<Long> idMovie;

    public CharacterFiltersDTO(String name, Integer age, Double weight, Set<Long> idMovie) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.idMovie = idMovie;
    }
}
