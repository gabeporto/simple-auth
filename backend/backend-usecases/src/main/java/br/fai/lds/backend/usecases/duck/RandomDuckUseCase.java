package br.fai.lds.backend.usecases.duck;

import br.fai.lds.domain.dto.DuckDto;
import org.springframework.web.client.RestTemplate;

public class RandomDuckUseCase {
    private String externalApiUrl = "https://random-d.uk/api/v2";
    public DuckDto getRandomDuck() {
        String apiUrl = externalApiUrl + "/random";

        RestTemplate restTemplate = new RestTemplate();
        DuckDto randomDuck = restTemplate.getForObject(apiUrl, DuckDto.class);

        return randomDuck;
    }
}

