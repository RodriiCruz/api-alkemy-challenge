package com.challenge.alkemy.api.repository;

import com.challenge.alkemy.api.entity.MovieEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rodrigo Cruz
 */
@Repository
public interface IMovieRepository extends JpaRepository<MovieEntity, Long> {
    
    Optional<MovieEntity> findByTitle(String title);

    List<MovieEntity> findAll(Specification<MovieEntity> specification);

}
