package fr.alexisvachard.microservices.filmservice.persistence.v1.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "personality"
)
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "VARCHAR(30)")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "VARCHAR(30)")
    private String lastName;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String bioEn;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String bioFr;

    public Personality() {
    }

    public Personality(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String bioEn, @NotBlank String bioFr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bioEn = bioEn;
        this.bioFr = bioFr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBioEn() {
        return bioEn;
    }

    public void setBioEn(String bioEn) {
        this.bioEn = bioEn;
    }

    public String getBioFr() {
        return bioFr;
    }

    public void setBioFr(String bioFr) {
        this.bioFr = bioFr;
    }
}
