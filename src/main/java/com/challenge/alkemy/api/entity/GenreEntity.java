package com.challenge.alkemy.api.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
@Entity
@Table(name = "genres")
public class GenreEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String image;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.REMOVE)
    private List<MovieEntity> movies;
}
