package fr.alexisvachard.microservices.filmservice.web.v1;

import fr.alexisvachard.microservices.filmservice.persistence.model.Film;
import fr.alexisvachard.microservices.filmservice.web.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/film")
public class FilmControllerV1 {

    @GetMapping
    public ResponseEntity<?> getFilm(){

        return new ResponseEntity<>(
                new ApiResponse<>(
                        new Film(
                                "Inception",
                                "Inception"
                        ),
                        true,
                        HttpStatus.OK.value(),
                        "Success"
                ),
                HttpStatus.OK
        );
    }
}
