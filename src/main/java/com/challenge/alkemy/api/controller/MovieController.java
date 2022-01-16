package com.challenge.alkemy.api.controller;

import com.challenge.alkemy.api.dto.GetMovieDTO;
import com.challenge.alkemy.api.dto.MovieDTO;
import com.challenge.alkemy.api.dto.NewMovieDTO;
import com.challenge.alkemy.api.service.IMovieService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rodrigo Cruz
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    public ResponseEntity<?> movies() {
        List<GetMovieDTO> movieDTO = movieService.getAll();

        if (!movieDTO.isEmpty()) {
            return new ResponseEntity<>(movieDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping //TODO chequear si recibe un json incorrecto
    public ResponseEntity<MovieDTO> save(@RequestBody NewMovieDTO movieDTO) {
        MovieDTO movie = movieService.save(movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> edit(@RequestBody NewMovieDTO movieDTO, @PathVariable Long id) {
        MovieDTO movie = movieService.edit(id, movieDTO);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        movieService.delete(id);
        return new ResponseEntity<>("La pelicula o serie fue borrada correctamente", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        MovieDTO movie = movieService.findById(id);

        ResponseEntity response;

        if (movie != null) {
            response = new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return response;
    }
    
    @GetMapping(value = "/find-by")
    public ResponseEntity<List<MovieDTO>> getByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long idGenre,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<MovieDTO> movies = movieService.getByFilters(title, idGenre, order);

        if (!movies.isEmpty()) {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity("No hay personajes que coincidan con los filtros aplicados", HttpStatus.NO_CONTENT);
        }
    }
}
