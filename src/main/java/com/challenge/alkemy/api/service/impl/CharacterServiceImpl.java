package com.challenge.alkemy.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.challenge.alkemy.api.dto.CharacterDTO;
import com.challenge.alkemy.api.dto.GetCharacterDTO;
import com.challenge.alkemy.api.dto.NewCharacterDTO;
import com.challenge.alkemy.api.dto.filters.CharacterFiltersDTO;
import com.challenge.alkemy.api.entity.CharacterEntity;
import com.challenge.alkemy.api.entity.MovieEntity;
import com.challenge.alkemy.api.exception.NotFoundException;
import com.challenge.alkemy.api.mapper.CharacterMapper;
import com.challenge.alkemy.api.repository.ICharacterRepository;
import com.challenge.alkemy.api.repository.specifications.CharacterSpecification;
import com.challenge.alkemy.api.service.ICharacterService;
import java.util.Set;

/**
 *
 * @author Rodrigo Cruz
 */
@Service
public class CharacterServiceImpl implements ICharacterService {

    @Autowired
    private ICharacterRepository characterRepository;

    @Autowired
    private CharacterMapper characterMapper;
    
    @Autowired
    private CharacterSpecification characterSpecification;

    @Transactional
    @Override
    public CharacterDTO save(NewCharacterDTO newCharacter) {

        CharacterEntity character = characterMapper.newCharacterDto2Entity(newCharacter);

        CharacterEntity characterSaved = characterRepository.save(character);

        CharacterDTO dto = characterMapper.characterEntity2DTO(characterSaved);

        return dto;
    }

    @Transactional
    @Override
    public CharacterDTO edit(Long id, NewCharacterDTO newCharacter) throws NotFoundException {

        Optional<CharacterEntity> respuesta = characterRepository.findById(id);

        if (respuesta.isPresent()) {

            CharacterEntity character = respuesta.get();

            character.setImage(newCharacter.getImage());
            character.setName(newCharacter.getName());
            character.setAge(newCharacter.getAge());
            character.setWeight(newCharacter.getWeight());
            character.setHistory(newCharacter.getHistory());

            List<MovieEntity> newMovies = characterMapper.getMoviesByIdMovie(newCharacter.getIdMovies());

            //Borro el personaje de cada pelicula de la coleccion antigua
            for (MovieEntity movie : character.getMovies()) {
                movie.getCharacters().remove(character);
            }

            //Agrego al personaje a cada pelicula de la lista nueva
            for (MovieEntity movie2 : newMovies) {
                movie2.getCharacters().add(character);
            }

            character.setMovies(newMovies);

            CharacterEntity characterSaved = characterRepository.save(character);

            CharacterDTO dto = characterMapper.characterEntity2DTO(characterSaved);

            return dto;
        } else {
            throw new NotFoundException("No existe el personaje que desea modificar");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {

        Optional<CharacterEntity> result = characterRepository.findById(id);

        if (result.isPresent()) {
            CharacterEntity c = result.get();

            for (MovieEntity movie : c.getMovies()) {
                movie.getCharacters().remove(c);
            }

            characterRepository.deleteById(id);
        } else {
            throw new NotFoundException("No existe el personaje que desea borrar");
        }
    }

    @Transactional
    @Override
    public CharacterDTO getCharacter(Long id) throws NotFoundException {

        Optional<CharacterEntity> result = characterRepository.findById(id);

        if (result.isPresent()) {

            CharacterEntity character = result.get();

            CharacterDTO dto = characterMapper.characterEntity2DTO(character);

            return dto;
        } else {
            throw new NotFoundException("No existe el personaje que desea ver");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetCharacterDTO> getAll() {

        List<CharacterEntity> characters = characterRepository.findAll();

        List<GetCharacterDTO> charactersDTO = new ArrayList<>();

        for (CharacterEntity character : characters) {
            GetCharacterDTO getCharacter = characterMapper.characterEntity2GetCharacter(character);

            charactersDTO.add(getCharacter);
        }

        return charactersDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CharacterDTO> getByFilters(String name, Integer age, Double weight, Set<Long> idMovie) {
        CharacterFiltersDTO filters = new CharacterFiltersDTO(name, age, weight, idMovie);
        
        List<CharacterEntity> characters = characterRepository.findAll(characterSpecification.getByFilters(filters));
        
        List<CharacterDTO> dtos = new ArrayList<>();
        
        for (CharacterEntity entity : characters) {
            CharacterDTO c = characterMapper.characterEntity2DTO(entity);
            dtos.add(c);
        }
        
        return dtos;
    }
}
