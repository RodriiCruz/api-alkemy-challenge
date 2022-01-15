package com.challenge.alkemy.api.repository.specifications;

import com.challenge.alkemy.api.dto.filters.CharacterFiltersDTO;
import com.challenge.alkemy.api.entity.CharacterEntity;
import com.challenge.alkemy.api.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author Rodrigo Cruz
 */
@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filters.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filters.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filters.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filters.getAge())
                );
            }

            if (filters.getWeight() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("weight"), filters.getWeight())
                );
            }

            if (!CollectionUtils.isEmpty(filters.getIdMovie())) {
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filters.getIdMovie()));
            }

            query.distinct(true); //Remove duplicates

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
