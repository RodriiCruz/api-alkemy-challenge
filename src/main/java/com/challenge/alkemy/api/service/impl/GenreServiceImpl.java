package com.challenge.alkemy.api.service.impl;

import com.challenge.alkemy.api.dto.GenreDTO;
import com.challenge.alkemy.api.entity.GenreEntity;
import com.challenge.alkemy.api.exception.NotFoundException;
import com.challenge.alkemy.api.mapper.GenreMapper;
import com.challenge.alkemy.api.repository.IGenreRepository;
import com.challenge.alkemy.api.service.IGenreService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rodrigo Cruz
 */
@Service
public class GenreServiceImpl implements IGenreService {

    @Autowired
    private IGenreRepository iGenreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Transactional
    @Override
    public GenreDTO save(GenreDTO genreDTO) {
        GenreEntity genre = genreMapper.genreDTO2Entity(genreDTO);

        GenreEntity genreSaved = iGenreRepository.save(genre);

        GenreDTO dto = genreMapper.genreEntity2DTO(genreSaved);

        return dto;
    }

    @Transactional
    @Override
    public GenreDTO edit(Long id, GenreDTO genreDTO) throws NotFoundException {
        if (id != null) {
            Optional<GenreEntity> result = iGenreRepository.findById(id);

            if (result.isPresent()) {
                GenreEntity genre = result.get();
                genre.setImage(genreDTO.getImage());
                genre.setName(genreDTO.getName());

                GenreEntity genreSaved = iGenreRepository.save(genre);

                GenreDTO dto = genreMapper.genreEntity2DTO(genreSaved);
                return dto;
            } else {
                throw new NotFoundException("No existe el genero que desea modificar");
            }
        } else {
            throw new NotFoundException("Debe ingresar el id del genero que desea modificar");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {
        if (id != null) {

            Optional<GenreEntity> respuesta = iGenreRepository.findById(id);

            if (respuesta.isPresent()) {

                iGenreRepository.deleteById(id);

            } else {
                throw new NotFoundException("No existe el genero que desea borrar");
            }
        } else {
            throw new NotFoundException("Debe ingresar el id del genero que desea borrar");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenreDTO> getAll() {

        List<GenreEntity> genres = iGenreRepository.findAll();

        List<GenreDTO> genresDTO = genreMapper.ListGenreEntity2DTO(genres);

        return genresDTO;
    }
}
