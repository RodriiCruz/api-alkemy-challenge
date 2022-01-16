package com.challenge.alkemy.api.repository.specifications;

import com.challenge.alkemy.api.dto.filters.MovieFiltersDTO;
import com.challenge.alkemy.api.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author Rodrigo Cruz
 */
@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filters.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filters.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if (filters.getIdGenre() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("idGenre"), filters.getIdGenre())
                );
            }

            query.distinct(true); //Remove duplicates

            String orderByField = "creationDate";
            query.orderBy(
                    filters.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
