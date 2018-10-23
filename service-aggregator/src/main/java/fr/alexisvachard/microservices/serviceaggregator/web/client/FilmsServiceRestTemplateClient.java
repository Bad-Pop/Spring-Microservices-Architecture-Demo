package fr.alexisvachard.microservices.serviceaggregator.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FilmsServiceRestTemplateClient<T> {

    private RestTemplate restTemplate;

    @Autowired
    public FilmsServiceRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity findById(Integer id) {

        return restTemplate.exchange(
                "http://films-service/api/v1/film/search/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<T>() {
                }
        );
    }
}
