package fr.alexisvachard.microservices.films.persistence.dao;

import fr.alexisvachard.microservices.films.persistence.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<List<Film>> findByIdIn(List<Long> filmIds);
    Optional<List<Film>> findByTitleContaining(String title);
    Optional<Film> findByTitle(String title);

    Boolean existsByTitle(String title);

    Page<Film> findAll(Pageable pageable);
}
