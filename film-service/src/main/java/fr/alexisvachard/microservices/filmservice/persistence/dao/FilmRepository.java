package fr.alexisvachard.microservices.filmservice.persistence.dao;

import fr.alexisvachard.microservices.filmservice.persistence.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByIdIn(List<Long> filmIds);

    List<Film> findByTitleFrContaining(String title);
    List<Film> findByTitleEnContaining(String title);

    Optional<Film> findByTitleFr(String title);
    Optional<Film> findByTitleEn(String title);

    Boolean existsByTitleFr(String title);
    Boolean existsByTitleEn(String title);

    Page<Film> findAll(Pageable pageable);
}
