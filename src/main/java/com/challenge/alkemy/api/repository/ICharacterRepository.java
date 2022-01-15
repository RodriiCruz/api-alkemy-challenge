package com.challenge.alkemy.api.repository;

import com.challenge.alkemy.api.entity.CharacterEntity;
import java.util.List;
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

    @Override
    List<CharacterEntity> findAll(Specification<CharacterEntity> specification);
}
