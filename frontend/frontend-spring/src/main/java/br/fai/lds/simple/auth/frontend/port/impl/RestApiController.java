package br.fai.lds.simple.auth.frontend.port.impl;

import br.fai.lds.frontend.usecases.port.RestService;
import com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestApiController<T> implements RestService<T> {

    private HttpHeaders getHttpHeaders(final String token) {
        final HttpHeaders httpHeaders = new HttpHeaders();

        if(token == null) return httpHeaders;

        httpHeaders.set("Authorization", "Bearer " + token);
        return httpHeaders;
    }

    @Override
    public List<T> get(String resource) {

        return get(resource, null);
    }

    @Override
    public List<T> get(String resource, String token) {

        final String endpoint = RestApiHelper.getEndpoint(resource);
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<String> httpEntity = new HttpEntity<>("", getHttpHeaders(token));

        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<>() {
                });

        List<T> body = responseEntity.getBody();

        return body;
    }

    @Override
    public T getById(String resource, Class<T> clazz) {

        return getById(resource, clazz, null);
    }

    @Override
    public T getById(String resource, Class<T> clazz, String token) {

        try {
            final String endpoint = RestApiHelper.getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>("", getHttpHeaders(token));

            final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                final Gson gson = new Gson();
                final T responseObject = gson.fromJson(responseEntity.getBody(), clazz);
                return responseObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int post(String resource, T entity) {
        return post(resource, entity, null);
    }

    @Override
    public int post(String resource, T entity, String token) {
        try {
            final String endpoint = RestApiHelper.getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<T> httpEntity = new HttpEntity<>(entity, getHttpHeaders(token));

            final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                final String body = responseEntity.getBody();
                int createdId = Integer.parseInt(body);
                return createdId;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public boolean put(String resource, T entity) {

        return put(resource, entity, null);
    }

    @Override
    public boolean put(String resource, T entity, String token) {
        try {
            final String endpoint = RestApiHelper.getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<T> httpEntity = new HttpEntity<>(entity, getHttpHeaders(token));

            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, httpEntity, Boolean.class);

            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String resource) {

        return delete(resource, null);
    }

    @Override
    public boolean delete(String resource, String token) {
        try {
            final String endpoint = RestApiHelper.getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>("", getHttpHeaders(token));

            final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint, HttpMethod.DELETE, httpEntity, Boolean.class);

            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
