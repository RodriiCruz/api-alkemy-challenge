package com.challenge.alkemy.api.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class GetMovieDTO {

    private String image;
    private String title;
    private Date creationDate;
}
