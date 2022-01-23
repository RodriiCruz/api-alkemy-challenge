package com.challenge.alkemy.api.repository;

import com.challenge.alkemy.api.entity.CharacterEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rodrigo Cruz
 */
@Repository
public interface ICharacterRepository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {

    Optional<CharacterEntity> findByName(String name);
    
    @Override
    List<CharacterEntity> findAll(Specification<CharacterEntity> specification);
}
