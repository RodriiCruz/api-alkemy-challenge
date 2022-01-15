package com.challenge.alkemy.api.mapper;

import com.challenge.alkemy.api.dto.CharacterDTO;
import com.challenge.alkemy.api.dto.GetCharacterDTO;
import com.challenge.alkemy.api.dto.GetMovieDTO;
import com.challenge.alkemy.api.dto.NewCharacterDTO;
import com.challenge.alkemy.api.entity.CharacterEntity;
import com.challenge.alkemy.api.entity.MovieEntity;
import com.challenge.alkemy.api.exception.NotFoundException;
import com.challenge.alkemy.api.repository.IMovieRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Cruz
 */
@Component
public class CharacterMapper {

    @Autowired
    private IMovieRepository iMovieRepository;

    public CharacterEntity newCharacterDto2Entity(NewCharacterDTO character) {

        CharacterEntity entity = new CharacterEntity();

        entity.setImage(character.getImage());
        entity.setName(character.getName());
        entity.setAge(character.getAge());
        entity.setWeight(character.getWeight());
        entity.setHistory(character.getHistory());
        entity.setMovies(this.getMoviesByIdMovie(character.getIdMovies()));

        return entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity character) {

        CharacterDTO dto = new CharacterDTO();

        dto.setId(character.getId());
        dto.setImage(character.getImage());
        dto.setName(character.getName());
        dto.setAge(character.getAge());
        dto.setWeight(character.getWeight());
        dto.setHistory(character.getHistory());

        List<GetMovieDTO> moviesDTO = new ArrayList<>();

        //TODO traer esto desde movieService!
        for (MovieEntity movie : character.getMovies()) {
            GetMovieDTO movieDTO = new GetMovieDTO();

            movieDTO.setImage(movie.getImage());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setCreationDate(movie.getCreationDate());

            moviesDTO.add(movieDTO);
        }

        dto.setMovies(moviesDTO);

        return dto;
    }

    public GetCharacterDTO characterEntity2GetCharacter(CharacterEntity character) {

        GetCharacterDTO dto = new GetCharacterDTO();

        dto.setImage(character.getImage());
        dto.setName(character.getName());
        
        return dto;
    }

    //TODO pasar a servicio movie!!!!
    public List<MovieEntity> getMoviesByIdMovie(List<Long> idMovies) throws NotFoundException {

        List<MovieEntity> movies = new ArrayList<>();

        for (Long id : idMovies) {
            Optional<MovieEntity> result = iMovieRepository.findById(id);

            if (result.isPresent()) {

                MovieEntity movie = result.get();
                movies.add(movie);

            } else {
                throw new NotFoundException("No existe ninguna pelicula con el id ingresado");
            }
        }
        return movies;
    }
}
