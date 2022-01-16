package com.challenge.alkemy.api.dto.filters;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
public class MovieFiltersDTO {

    private String title;
    private Long idGenre;
    private String order;

    public MovieFiltersDTO(String title, Long idGenre, String order) {
        this.title = title;
        this.idGenre = idGenre;
        this.order = order;
    }

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }
}
