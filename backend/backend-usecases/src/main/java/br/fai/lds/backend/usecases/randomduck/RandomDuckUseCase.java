package br.fai.lds.backend.usecases.randomduck;

import br.fai.lds.domain.dto.RandomDuckDto;
import org.springframework.web.client.RestTemplate;

public class RandomDuckUseCase {
    private String externalApiUrl = "https://random-d.uk/api/v2";
    public RandomDuckDto getRandomDuck() {
        String apiUrl = externalApiUrl + "/random";

        RestTemplate restTemplate = new RestTemplate();
        RandomDuckDto randomDuck = restTemplate.getForObject(apiUrl, RandomDuckDto.class);

        return randomDuck;
    }
}

