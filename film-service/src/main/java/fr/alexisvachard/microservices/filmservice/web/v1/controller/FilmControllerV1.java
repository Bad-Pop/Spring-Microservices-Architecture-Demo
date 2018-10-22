package fr.alexisvachard.microservices.filmservice.web.v1.controller;

import fr.alexisvachard.microservices.filmservice.exception.ResourceNotFoundException;
import fr.alexisvachard.microservices.filmservice.persistence.v1.model.Film;
import fr.alexisvachard.microservices.filmservice.service.v1.FilmServiceV1;
import fr.alexisvachard.microservices.filmservice.web.v1.response.ApiResponseV1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/film")
public class FilmControllerV1 {

    private FilmServiceV1 filmService;

    @Autowired
    public FilmControllerV1(FilmServiceV1 filmService) {
        this.filmService = filmService;
    }

    @GetMapping(path = "/search/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException {

        try {
            Long lid = id.longValue();

            return filmService.findById(lid);
        } catch (ResourceNotFoundException rnfe) {
            throw new ResourceNotFoundException(rnfe);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponseV1<>(
                            false,
                            HttpStatus.BAD_REQUEST.value(),
                            "Please provide a valid ID !"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(path = "/search-by-ids")
    public ResponseEntity<?> findByIdIn(@RequestParam(name = "id") List<Integer> ids) {

        if (ids.isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponseV1<>(
                            false,
                            HttpStatus.BAD_REQUEST.value(),
                            "Please send a correct request like : http://<url>:<port>/api/v1/film/search-by-ids?id=1&id=9"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }

        List<Long> lids = new ArrayList<>();

        for (Integer i : ids) {
            lids.add(Long.valueOf(i.toString()));
        }

        return filmService.findByIdIn(lids);
    }

    @GetMapping(path = "/search-by-title")
    public ResponseEntity<?> findByTitleContaining(@RequestParam(name = "title") String title) {

        if (StringUtils.isEmpty(title)) {
            return new ResponseEntity<>(
                    new ApiResponseV1<>(
                            false,
                            HttpStatus.BAD_REQUEST.value(),
                            "Please send a correct request like : http://<url>:<port>/api/v1/film//search-by-title?title=Inception"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }

        return filmService.findByTitleContaining(title);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Film film) {

        if (StringUtils.isEmpty(film.getTitleEn()) || StringUtils.isEmpty(film.getTitleFr())) {
            return new ResponseEntity<>(
                    new ApiResponseV1<>(
                            false,
                            HttpStatus.BAD_REQUEST.value(),
                            "Please enter a valid title in French and in English !"
                    ),
                    HttpStatus.BAD_REQUEST
            );
        }

        return filmService.create(film);
    }
}
