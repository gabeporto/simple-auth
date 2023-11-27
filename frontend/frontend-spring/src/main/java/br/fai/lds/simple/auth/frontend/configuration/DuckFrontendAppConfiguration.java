package br.fai.lds.simple.auth.frontend.configuration;

import br.fai.lds.frontend.usecases.duck.RandomDuckUseCase;
import br.fai.lds.frontend.usecases.port.DuckRestService;
import br.fai.lds.simple.auth.frontend.port.impl.DuckRestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DuckFrontendAppConfiguration {

    @Bean
    public RandomDuckUseCase randomDuckUseCase() {
        final DuckRestService duckRestService = new DuckRestApiController();
        return new RandomDuckUseCase(duckRestService);
    }
}
