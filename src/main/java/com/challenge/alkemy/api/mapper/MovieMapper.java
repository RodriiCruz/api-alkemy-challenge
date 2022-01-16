package com.challenge.alkemy.api.mapper;

import com.challenge.alkemy.api.dto.GenreDTO;
import com.challenge.alkemy.api.dto.GetCharacterDTO;
import com.challenge.alkemy.api.dto.GetMovieDTO;
import com.challenge.alkemy.api.dto.MovieDTO;
import com.challenge.alkemy.api.dto.NewMovieDTO;
import com.challenge.alkemy.api.entity.CharacterEntity;
import com.challenge.alkemy.api.entity.GenreEntity;
import com.challenge.alkemy.api.entity.MovieEntity;
import com.challenge.alkemy.api.exception.NotFoundException;
import com.challenge.alkemy.api.repository.ICharacterRepository;
import com.challenge.alkemy.api.repository.IGenreRepository;
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
public class MovieMapper {

    @Autowired
    private IGenreRepository iGenreRepository;

    @Autowired
    private ICharacterRepository iCharacterRepository;

    @Autowired
    private GenreMapper genreMapper;

    public MovieEntity newMovieDTO2Entity(NewMovieDTO movieDTO) throws NotFoundException {

        MovieEntity movie = new MovieEntity();

        movie.setImage(movieDTO.getImage());
        movie.setTitle(movieDTO.getTitle());
        movie.setCreationDate(movieDTO.getCreationDate());
        movie.setQualification(movieDTO.getQualification());
        movie.setCharacters(this.findCharactersById(movieDTO.getCharacters()));

        Optional<GenreEntity> result = iGenreRepository.findById(movieDTO.getIdGenre());

        if (result.isPresent()) {
            GenreEntity genre = result.get();
            movie.setGenre(genre);
            movie.setIdGenre(movieDTO.getIdGenre());
        } else {
            throw new NotFoundException("No existe el genero ingresado");
        }

        return movie;
    }

    public MovieDTO movieEntity2DTO(MovieEntity movie) {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setId(movie.getId());
        movieDTO.setImage(movie.getImage());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setCreationDate(movie.getCreationDate());
        movieDTO.setQualification(movie.getQualification());

        List<GetCharacterDTO> listCharacters = new ArrayList<>();
        for (CharacterEntity character : movie.getCharacters()) {

            //TODO usar mapper de characters
            GetCharacterDTO characterDTO = new GetCharacterDTO();

            characterDTO.setImage(character.getImage());
            characterDTO.setName(character.getName());

            listCharacters.add(characterDTO);
        }

        movieDTO.setCharacters(listCharacters);

        GenreDTO genre = genreMapper.genreEntity2DTO(movie.getGenre());
        movieDTO.setGenre(genre);

        return movieDTO;
    }

    //usado para getAllMovies
    public GetMovieDTO movieEntity2GetMovie(MovieEntity movie) {
        GetMovieDTO dto = new GetMovieDTO();

        dto.setImage(movie.getImage());
        dto.setTitle(movie.getTitle());
        dto.setCreationDate(movie.getCreationDate());
        
        return dto;
    }

    //TODO llevarlo al service de personajes!!!!!!
    public List<CharacterEntity> findCharactersById(List<Long> idCharacter) throws NotFoundException {

        List<CharacterEntity> characters = new ArrayList<>();

        for (Long id : idCharacter) {
            Optional<CharacterEntity> respuesta = iCharacterRepository.findById(id);

            if (respuesta.isPresent()) {

                CharacterEntity character = respuesta.get();
                characters.add(character);
            } else {
                throw new NotFoundException("El id del personaje ingresado no existe");
            }
        }
        return characters;
    }
}
