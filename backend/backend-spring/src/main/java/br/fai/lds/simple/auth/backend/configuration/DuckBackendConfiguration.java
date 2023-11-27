package br.fai.lds.simple.auth.backend.configuration;

import br.fai.lds.backend.usecases.duck.RandomDuckUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DuckBackendConfiguration {

    @Bean
    public RandomDuckUseCase randomDuckUseCase() {
        return new RandomDuckUseCase();
    }
}
