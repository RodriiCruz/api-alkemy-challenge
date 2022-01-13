package com.challenge.alkemy.api.mapper;

import com.challenge.alkemy.api.dto.GenreDTO;
import com.challenge.alkemy.api.entity.GenreEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Cruz
 */
@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO genreDTO) {
        GenreEntity genre = new GenreEntity();

        genre.setImage(genreDTO.getImage());
        genre.setName(genreDTO.getName());

        return genre;
    }

    public GenreDTO genreEntity2DTO(GenreEntity genreEntity) {
        GenreDTO genre = new GenreDTO();

        genre.setId(genreEntity.getId());
        genre.setImage(genreEntity.getImage());
        genre.setName(genreEntity.getName());

        return genre;
    }

    public List<GenreDTO> ListGenreEntity2DTO(List<GenreEntity> entities) {
        List<GenreDTO> genres = new ArrayList<>();

        entities.forEach(g -> {
            genres.add(genreEntity2DTO(g));
        });

        return genres;
    }
}
