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
public class NewCharacterDTO {

    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String history;
    private List<Long> idMovies;
}
