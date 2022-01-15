package com.challenge.alkemy.api.service.impl;

import com.challenge.alkemy.api.dto.GetMovieDTO;
import com.challenge.alkemy.api.dto.MovieDTO;
import com.challenge.alkemy.api.dto.NewMovieDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.challenge.alkemy.api.entity.GenreEntity;
import com.challenge.alkemy.api.entity.MovieEntity;
import com.challenge.alkemy.api.exception.NotFoundException;
import com.challenge.alkemy.api.mapper.MovieMapper;
import com.challenge.alkemy.api.repository.IGenreRepository;
import com.challenge.alkemy.api.repository.IMovieRepository;
import com.challenge.alkemy.api.service.IMovieService;

/**
 *
 * @author Rodrigo Cruz
 */
@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private IMovieRepository iMovieRepository;

    @Autowired
    private IGenreRepository iGenreRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Transactional
    @Override
    public MovieDTO save(NewMovieDTO movieDTO) {

        MovieEntity movie = movieMapper.newMovieDTO2Entity(movieDTO);

        MovieEntity movieSaved = iMovieRepository.save(movie);

        MovieDTO dto = movieMapper.movieEntity2DTO(movieSaved);

        return dto;
    }

    @Transactional
    @Override
    public MovieDTO edit(Long id, NewMovieDTO movieDTO) throws NotFoundException {

            Optional<MovieEntity> respuesta = iMovieRepository.findById(id);

            if (respuesta.isPresent()) {

                MovieEntity movie = respuesta.get();

                movie.setImage(movieDTO.getImage());
                movie.setTitle(movieDTO.getTitle());
                movie.setCreationDate(movieDTO.getCreationDate());
                movie.setQualification(movieDTO.getQualification());
                movie.setCharacters(movieMapper.findCharactersById(movieDTO.getCharacters()));

                Optional<GenreEntity> respuestaGenero = iGenreRepository.findById(movieDTO.getIdGenre());

                if (respuesta.isPresent()) {
                    GenreEntity genre = respuestaGenero.get();
                    movie.setGenre(genre);
                } else {
                    throw new NotFoundException("No existe el genero ingresado");
                }

                MovieDTO dto = movieMapper.movieEntity2DTO(movie);

                return dto;
            } else {
                throw new NotFoundException("No existe la pelicula o serie que desea modificar");
            }
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {

            Optional<MovieEntity> respuesta = iMovieRepository.findById(id);

            if (respuesta.isPresent()) {
                iMovieRepository.deleteById(id);
            } else {
                throw new NotFoundException("No existe la pelicula o serie que desea borrar");
            }
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetMovieDTO> getAll() {

        List<MovieEntity> movies = iMovieRepository.findAll();
        List<GetMovieDTO> moviesDTO = new ArrayList<>();

        for (MovieEntity p : movies) {
            GetMovieDTO dto = movieMapper.movieEntity2GetMovie(p);
            moviesDTO.add(dto);
        }

        return moviesDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public MovieDTO findById(Long id) throws NotFoundException {

            Optional<MovieEntity> respuesta = iMovieRepository.findById(id);

            if (respuesta.isPresent()) {
                MovieEntity movie = respuesta.get();
                MovieDTO dto = movieMapper.movieEntity2DTO(movie);

                return dto;

            } else {
                throw new NotFoundException("No existe la pelicula o serie que desea ver");
            }
    }
}
