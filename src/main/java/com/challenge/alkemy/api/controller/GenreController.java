package com.challenge.alkemy.api.controller;

import com.challenge.alkemy.api.dto.GenreDTO;
import com.challenge.alkemy.api.service.IGenreService;
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
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private IGenreService iGenreService;

    @GetMapping(value = "")
    public ResponseEntity<List<GenreDTO>> getAll() {
        List<GenreDTO> genresDTO = iGenreService.getAll();

        ResponseEntity<List<GenreDTO>> response;

        if (!genresDTO.isEmpty()) {
            response = new ResponseEntity<>(genresDTO, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO gDTO) {
        GenreDTO genreSaved = iGenreService.save(gDTO);
        return new ResponseEntity<>(genreSaved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenreDTO> edit(@RequestBody GenreDTO genreDTO, @PathVariable Long id) {
        GenreDTO dtoEdited = iGenreService.edit(id, genreDTO);
        return new ResponseEntity<>(dtoEdited, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        iGenreService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
