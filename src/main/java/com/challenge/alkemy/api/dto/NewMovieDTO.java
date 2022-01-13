package com.challenge.alkemy.api.dto;

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
public class NewMovieDTO {

    private String image;
    private String title;
    private Date creationDate;
    private Integer qualification;
    private Long idGenre;
    private List<Long> characters;
}
