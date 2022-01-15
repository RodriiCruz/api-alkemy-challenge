package com.challenge.alkemy.api.controller;

import com.challenge.alkemy.api.dto.CharacterDTO;
import com.challenge.alkemy.api.dto.GetCharacterDTO;
import com.challenge.alkemy.api.dto.NewCharacterDTO;
import com.challenge.alkemy.api.service.ICharacterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rodrigo Cruz
 */
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private ICharacterService characterService;

    @GetMapping(value = "")
    public ResponseEntity<List<GetCharacterDTO>> characters() {
        List<GetCharacterDTO> charactersDTO = characterService.getAll();

        if (!charactersDTO.isEmpty()) {
            return new ResponseEntity<>(charactersDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody NewCharacterDTO newCharacter) {
        CharacterDTO character = characterService.save(newCharacter);
        return new ResponseEntity<>(character, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CharacterDTO> edit(@RequestBody NewCharacterDTO newCharacter, @PathVariable Long id) {
        CharacterDTO character = characterService.edit(id, newCharacter);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        characterService.delete(id);
        return new ResponseEntity<>("El personaje fue borrado correctamente", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable("id") Long id) {

        CharacterDTO character = characterService.getCharacter(id);

        if (character != null) {
            return new ResponseEntity<>(character, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
