package fr.alexisvachard.microservices.filmservice.service.v1;

import fr.alexisvachard.microservices.filmservice.exception.AppException;
import fr.alexisvachard.microservices.filmservice.exception.ResourceNotFoundException;
import fr.alexisvachard.microservices.filmservice.persistence.v1.dao.FilmRepository;
import fr.alexisvachard.microservices.filmservice.persistence.v1.model.Film;
import fr.alexisvachard.microservices.filmservice.web.v1.response.ApiResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class FilmServiceV1 {

    private FilmRepository filmRepository;

    @Autowired
    public FilmServiceV1(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public ResponseEntity<?> findById(Long id) throws AppException {

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The film",
                        "the given id",
                        "Is this id good ?"
                ));

        return new ResponseEntity<>(
                new ApiResponseV1<>(
                        film,
                        true,
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );

    }

    public ResponseEntity<?> findByIdIn(List<Long> ids) {

        List<Film> films = filmRepository.findByIdIn(ids);

        if (films.isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponseV1<>(
                            false,
                            HttpStatus.NOT_FOUND.value(),
                            "Unable to find film(s) with the given ID(s)"
                    ),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                new ApiResponseV1<>(
                        films,
                        true,
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    public ResponseEntity<?> findByTitleContaining(String title) {

        // /!\ FUZZY SEARCH /!\
        Map<Long, Film> films = new TreeMap<>();
        List<Film> lfilms = new ArrayList<>();
        lfilms.addAll(filmRepository.findFilmByTitleEnContainingIgnoreCase(title));
        lfilms.addAll(filmRepository.findFilmByTitleFrContainingIgnoreCase(title));


        if (lfilms.isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponseV1<>(
                            false,
                            HttpStatus.NOT_FOUND.value(),
                            "No result found for your request"
                    ),
                    HttpStatus.NOT_FOUND
            );
        }

        for (Film f : lfilms) {
            films.putIfAbsent(f.getId(), f);
        }

        return new ResponseEntity<>(
                new ApiResponseV1<>(
                        films,
                        true,
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    public ResponseEntity<?> create(Film film) {

        Film enFilmCheck = filmRepository.findByTitleEn(film.getTitleEn());
        Film frFilmCheck = filmRepository.findByTitleEn(film.getTitleFr());

        if (enFilmCheck != null || frFilmCheck != null) {
            return new ResponseEntity<>(
                    new ApiResponseV1<>(
                            enFilmCheck,
                            false,
                            HttpStatus.CONFLICT.value(),
                            "This film is already in our database !"
                    ),
                    HttpStatus.CONFLICT
            );
        }

        Film fFilm = filmRepository.save(film);

        return new ResponseEntity<>(
                new ApiResponseV1<>(
                        fFilm,
                        true,
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }
}
