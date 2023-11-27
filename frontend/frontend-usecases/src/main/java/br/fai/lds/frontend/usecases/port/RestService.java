package br.fai.lds.frontend.usecases.port;

import java.util.List;

public interface RestService<T> {

    List<T> get(final String resource);
    List<T> get(final String resource, final String token);

    T getById(final String resource, Class<T> clazz);
    T getById(final String resource, Class<T> clazz, final String token);

    int post(final String resource, final T entity);
    int post(final String resource, final T entity, final String token);

    boolean put(final String resource, final T entity);
    boolean put(final String resource, final T entity, final String token);

    boolean delete(final String resource);
    boolean delete(final String resource, final String token);

}
