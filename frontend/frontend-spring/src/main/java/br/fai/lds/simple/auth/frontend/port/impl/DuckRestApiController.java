package br.fai.lds.simple.auth.frontend.port.impl;

import br.fai.lds.domain.dto.DuckDto;
import br.fai.lds.frontend.usecases.port.DuckRestService;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DuckRestApiController implements DuckRestService {

    @Override
    public DuckDto get(final String resource) {
        try {
            final String endpoint = RestApiHelper.getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>("");

            final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                final Gson gson = new Gson();
                final DuckDto responseObject = gson.fromJson(responseEntity.getBody(), DuckDto.class);
                return responseObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
