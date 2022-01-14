package com.challenge.alkemy.api.repository;

import com.challenge.alkemy.api.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rodrigo Cruz
 */
@Repository
public interface IMovieRepository extends JpaRepository<MovieEntity, Long> {

}
