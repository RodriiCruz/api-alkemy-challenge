package com.challenge.alkemy.api.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Rodrigo Cruz
 */
@Setter
@Getter
@Entity
@Table(name = "movies")
public class MovieEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    private Long id;

    @NotBlank
    private String image;

    @NotBlank
    private String title;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "creation_date")
    private Date creationDate;

    @NotNull
    @Min(value = 1 , message = "Calificación fuera de rango (1-5)")
    @Max(value = 5 , message = "Calificación fuera de rango (1-5)")
    private Integer qualification;

    @NotNull
    @OneToOne
    private GenreEntity genre;
    private Long idGenre;

    @ManyToMany
    private List<CharacterEntity> characters;
}
