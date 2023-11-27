package br.fai.lds.frontend.usecases.port;

import br.fai.lds.domain.dto.DuckDto;

public interface DuckRestService {
    DuckDto get(final String resource);
}
