package com.challenge.alkemy.api.service;

import com.challenge.alkemy.api.dto.GenreDTO;
import com.challenge.alkemy.api.exception.NotFoundException;
import java.util.List;

/**
 *
 * @author Rodrigo Cruz
 */
public interface IGenreService {

    GenreDTO save(GenreDTO genreDTO);

    GenreDTO edit(Long id, GenreDTO genreDTO) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    List<GenreDTO> getAll();
}
