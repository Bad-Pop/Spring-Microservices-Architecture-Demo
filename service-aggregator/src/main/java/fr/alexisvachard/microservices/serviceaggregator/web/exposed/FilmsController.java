package fr.alexisvachard.microservices.serviceaggregator.web.exposed;

import fr.alexisvachard.microservices.serviceaggregator.web.client.FilmsServiceRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/film")
public class FilmsController {

    private FilmsServiceRestTemplateClient filmsClient;

    @Autowired
    public FilmsController(FilmsServiceRestTemplateClient filmsClient) {
        this.filmsClient = filmsClient;
    }

    @GetMapping(path = "/search/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {

        return filmsClient.findById(id);
    }
}
