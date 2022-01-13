package com.challenge.alkemy.api.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Rodrigo Cruz
 */

@Setter
@Getter
@Entity
@Table(name = "characters")
public class CharacterEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_character")
    private Long id;

    @NotBlank
    private String image;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private Double weight;

    @NotBlank
    private String history;

    @ManyToMany(mappedBy = "characters")
    private List<MovieEntity> movies;
}