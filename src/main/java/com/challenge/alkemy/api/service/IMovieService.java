package com.challenge.alkemy.api.service;

import com.challenge.alkemy.api.dto.GetMovieDTO;
import com.challenge.alkemy.api.dto.MovieDTO;
import com.challenge.alkemy.api.dto.NewMovieDTO;
import com.challenge.alkemy.api.exception.NotFoundException;
import java.util.List;

/**
 *
 * @author Rodrigo Cruz
 */
public interface IMovieService {

    MovieDTO save(NewMovieDTO movieDTO);

    MovieDTO edit(Long id, NewMovieDTO movieDTO) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    List<GetMovieDTO> getAll();

    MovieDTO findById(Long id) throws NotFoundException;
}
