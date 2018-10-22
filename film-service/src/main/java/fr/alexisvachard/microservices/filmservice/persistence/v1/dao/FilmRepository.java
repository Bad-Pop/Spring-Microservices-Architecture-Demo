package fr.alexisvachard.microservices.filmservice.persistence.v1.dao;

import fr.alexisvachard.microservices.filmservice.persistence.v1.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByIdIn(List<Long> filmIds);

    List<Film> findFilmByTitleEnContainingIgnoreCase(String title);

    List<Film> findFilmByTitleFrContainingIgnoreCase(String title);

    Page<Film> findAll(Pageable pageable);

    Film findByTitleEn(String title);

    Film findByTitleFr(String title);
}
