package fr.alexisvachard.webservices.filmsaggregator.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FilmsServiceRestTemplateClient {

    private RestTemplate restTemplate;

    @Autowired
    public FilmsServiceRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getFilm(){

        String str = restTemplate.exchange(
                "http://films-titles-and-id-service/api/films",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }
        ).getBody();

        return str;
    }
}
