package com.challenge.alkemy.api.service.impl;

import com.challenge.alkemy.api.dto.GetMovieDTO;
import com.challenge.alkemy.api.dto.MovieDTO;
import com.challenge.alkemy.api.dto.NewMovieDTO;
import com.challenge.alkemy.api.dto.filters.MovieFiltersDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.challenge.alkemy.api.entity.GenreEntity;
import com.challenge.alkemy.api.entity.MovieEntity;
import com.challenge.alkemy.api.exception.AlreadyExistsException;
import com.challenge.alkemy.api.exception.NotFoundException;
import com.challenge.alkemy.api.mapper.MovieMapper;
import com.challenge.alkemy.api.repository.IGenreRepository;
import com.challenge.alkemy.api.repository.IMovieRepository;
import com.challenge.alkemy.api.repository.specifications.MovieSpecification;
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

    @Autowired
    private MovieSpecification movieSpecification;

    @Transactional
    @Override
    public MovieDTO save(NewMovieDTO movieDTO) {
        
        Optional<MovieEntity> result = iMovieRepository.findByTitle(movieDTO.getTitle());
        if (result.isPresent()) {
            throw new AlreadyExistsException("La pelicula/serie " + movieDTO.getTitle() + " ya se encuentra cargada");
        }

        MovieEntity movie = movieMapper.newMovieDTO2Entity(movieDTO);

        MovieEntity movieSaved = iMovieRepository.save(movie);

        MovieDTO dto = movieMapper.movieEntity2DTO(movieSaved);

        return dto;
    }

    @Transactional
    @Override
    public MovieDTO edit(Long id, NewMovieDTO movieDTO) throws NotFoundException {

        Optional<MovieEntity> resultMovie = iMovieRepository.findById(id);

        if (resultMovie.isPresent()) {

            MovieEntity movie = resultMovie.get();

            movie.setImage(movieDTO.getImage());
            movie.setTitle(movieDTO.getTitle());
            movie.setCreationDate(movieDTO.getCreationDate());
            movie.setQualification(movieDTO.getQualification());
            movie.setCharacters(movieMapper.findCharactersById(movieDTO.getCharacters()));

            Optional<GenreEntity> resultGenre = iGenreRepository.findById(movieDTO.getIdGenre());

            if (resultGenre.isPresent()) {
                GenreEntity genre = resultGenre.get();
                movie.setGenre(genre);
            } else {
                throw new NotFoundException("No existe ningun genero con el id ingresado");
            }

            MovieDTO dto = movieMapper.movieEntity2DTO(movie);

            return dto;
        } else {
            throw new NotFoundException("No existe ninguna pelicula/serie con el id ingresado");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {

        Optional<MovieEntity> result = iMovieRepository.findById(id);

        if (result.isPresent()) {
            iMovieRepository.deleteById(id);
        } else {
            throw new NotFoundException("No existe ninguna pelicula/serie con el id ingresado");
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

        Optional<MovieEntity> result = iMovieRepository.findById(id);

        if (result.isPresent()) {
            MovieEntity movie = result.get();
            MovieDTO dto = movieMapper.movieEntity2DTO(movie);

            return dto;

        } else {
            throw new NotFoundException("No existe ninguna pelicula/serie con el id ingresado");
        }
    }

    @Override
    public List<MovieDTO> getByFilters(String title, Long idGenre, String order) throws NotFoundException {
        MovieFiltersDTO filters = new MovieFiltersDTO(title, idGenre, order);

        List<MovieEntity> movies = iMovieRepository.findAll(movieSpecification.getByFilters(filters));

        List<MovieDTO> dtos = new ArrayList<>();

        for (MovieEntity entity : movies) {
            MovieDTO m = movieMapper.movieEntity2DTO(entity);
            dtos.add(m);
        }

        return dtos;
    }
}
