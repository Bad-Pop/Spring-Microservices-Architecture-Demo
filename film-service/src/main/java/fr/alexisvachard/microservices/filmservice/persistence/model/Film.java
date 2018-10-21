package fr.alexisvachard.microservices.filmservice.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "titleFr", nullable = false)
    private String titleFr;

    @NotNull
    @NotBlank
    @Column(name = "titleEn", nullable = false)
    private String titleEn;

    public Film() {
    }

    public Film( @NotBlank String titleFr, @NotBlank String titleEn) {
        this.titleFr = titleFr;
        this.titleEn = titleEn;
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
}
