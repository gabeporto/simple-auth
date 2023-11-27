package br.fai.lds.frontend.usecases.duck;

import br.fai.lds.domain.dto.DuckDto;
import br.fai.lds.frontend.usecases.port.DuckRestService;


public class RandomDuckUseCase {

    public final DuckRestService duckRestService;

    public RandomDuckUseCase(DuckRestService duckRestService) {
        this.duckRestService = duckRestService;
    }

    public DuckDto getRandomDuck() {

        final String resource = "/duck/random";
        return duckRestService.get(resource);
    }
}
