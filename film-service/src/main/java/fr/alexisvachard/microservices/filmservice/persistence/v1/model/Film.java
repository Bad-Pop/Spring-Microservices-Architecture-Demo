package fr.alexisvachard.microservices.filmservice.persistence.v1.model;

import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Indexed
@Entity
@Table(name = "Film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "titleFr", nullable = false, unique = true)
    private String titleFr;

    @NotNull
    @NotBlank
    @Column(name = "titleEn", nullable = false, unique = true)
    private String titleEn;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String descriptionEn;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String descriptionFr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "director",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "personality_id")
    )
    private Personality director;

    @OneToOne
    private Poster poster;


    public Film() {
    }

    public Film(@NotBlank String titleFr, @NotBlank String titleEn, @NotBlank String descriptionEn, @NotBlank String descriptionFr) {
        this.titleFr = titleFr;
        this.titleEn = titleEn;
        this.descriptionEn = descriptionEn;
        this.descriptionFr = descriptionFr;
    }

    public Film(@NotNull @NotBlank String titleFr, @NotNull @NotBlank String titleEn, @NotNull @NotBlank String descriptionEn, @NotNull @NotBlank String descriptionFr, Personality director, Poster poster) {
        this.titleFr = titleFr;
        this.titleEn = titleEn;
        this.descriptionEn = descriptionEn;
        this.descriptionFr = descriptionFr;
        this.director = director;
        this.poster = poster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleFr() {
        return titleFr;
    }

    public void setTitleFr(String titleFr) {
        this.titleFr = titleFr;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionFr() {
        return descriptionFr;
    }

    public void setDescriptionFr(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }

    public Personality getDirector() {
        return director;
    }

    public void setDirector(Personality director) {
        this.director = director;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }
}
