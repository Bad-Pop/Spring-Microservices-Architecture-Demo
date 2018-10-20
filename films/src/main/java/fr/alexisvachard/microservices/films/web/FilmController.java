package fr.alexisvachard.microservices.films.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/films")
public class FilmController {

    @GetMapping
    public ResponseEntity<?> getFilm(){
        return new ResponseEntity<String>("Ceci est un titre de film", HttpStatus.OK);
    }
}
