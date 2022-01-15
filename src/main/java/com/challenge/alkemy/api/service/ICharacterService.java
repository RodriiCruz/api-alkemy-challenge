package com.challenge.alkemy.api.service;

import com.challenge.alkemy.api.dto.CharacterDTO;
import com.challenge.alkemy.api.dto.GetCharacterDTO;
import com.challenge.alkemy.api.dto.NewCharacterDTO;
import com.challenge.alkemy.api.exception.NotFoundException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Rodrigo Cruz
 */
public interface ICharacterService {

    CharacterDTO save(NewCharacterDTO newCharacter);

    CharacterDTO edit(Long id, NewCharacterDTO newCharacter) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    CharacterDTO getCharacter(Long id) throws NotFoundException;

    List<GetCharacterDTO> getAll();

    List<CharacterDTO> getByFilters(String name, Integer age, Double weight, Set<Long> idMovie);
}
