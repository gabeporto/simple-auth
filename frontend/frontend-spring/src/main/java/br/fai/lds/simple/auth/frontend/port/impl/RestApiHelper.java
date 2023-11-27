package br.fai.lds.simple.auth.frontend.port.impl;

public class RestApiHelper {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api";

    public static String getEndpoint(final String resource) {
        return BASE_ENDPOINT + resource;
    }
}
