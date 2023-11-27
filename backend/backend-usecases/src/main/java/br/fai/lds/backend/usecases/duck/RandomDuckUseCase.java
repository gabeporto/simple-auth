package br.fai.lds.backend.usecases.duck;

import br.fai.lds.domain.dto.DuckDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RandomDuckUseCase {
    private String duckApiUrl = "https://random-d.uk/api/v2/random";
    private String adviceApiUrl = "https://api.adviceslip.com/advice";

    public DuckDto getRandomDuck() {
        try {
            DuckDto duckDto = new DuckDto();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<DuckDto> duckResponseEntity = restTemplate.getForEntity(duckApiUrl, DuckDto.class);

            if (duckResponseEntity.getStatusCode().is2xxSuccessful()) {
                DuckDto duckApiDto = duckResponseEntity.getBody();
                duckDto.setUrl(duckApiDto.getUrl());
            } else {
                System.err.println("Erro na resposta da API de Duck: " + duckResponseEntity.getStatusCode());
                return null;
            }

            ResponseEntity<String> adviceResponseEntity = restTemplate.getForEntity(adviceApiUrl, String.class);

            if (adviceResponseEntity.getStatusCode().is2xxSuccessful()) {
                String adviceResponseBody = adviceResponseEntity.getBody();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode adviceJsonNode = objectMapper.readTree(adviceResponseBody);

                String advice = adviceJsonNode.path("slip").path("advice").asText();
                duckDto.setMessage(advice);
            } else {
                System.err.println("Erro na resposta da API de Conselhos: " + adviceResponseEntity.getStatusCode());
                return null;
            }

            return duckDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
