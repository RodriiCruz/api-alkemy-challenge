package com.challenge.alkemy.api.repository;

import com.challenge.alkemy.api.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rodrigo Cruz
 */
@Repository
public interface IGenreRepository extends JpaRepository<GenreEntity, Long> {

}
