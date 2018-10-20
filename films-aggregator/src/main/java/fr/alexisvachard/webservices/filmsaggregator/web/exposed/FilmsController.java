package fr.alexisvachard.webservices.filmsaggregator.web.exposed;

import fr.alexisvachard.webservices.filmsaggregator.web.client.FilmsServiceRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/films")
public class FilmsController {

    private FilmsServiceRestTemplateClient filmsClient;

    @Autowired
    public FilmsController(FilmsServiceRestTemplateClient filmsClient) {
        this.filmsClient = filmsClient;
    }

    @GetMapping
    public ResponseEntity<?> getFilm(){
        return new ResponseEntity<String>(filmsClient.getFilm(), HttpStatus.OK);
    }
}
