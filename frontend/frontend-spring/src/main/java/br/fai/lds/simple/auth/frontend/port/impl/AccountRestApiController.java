package br.fai.lds.simple.auth.frontend.port.impl;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.AccountRestService;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class AccountRestApiController implements AccountRestService {

    private HttpHeaders getBasicHeaders(final String email, final String password) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("email", email);
        httpHeaders.set("password", password);

        return httpHeaders;
    }

    @Override
    public UserModel validateCredentials(String email, String password) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders httpHeaders = getBasicHeaders(email, password);
            httpHeaders.set("email", email);
            httpHeaders.set("password", password);

            final HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

            final String endpoint = "http://localhost:8081/api/authentication/login";

            ResponseEntity<UserModel> responseEntity = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    httpEntity,
                    UserModel.class);

            if(responseEntity.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
