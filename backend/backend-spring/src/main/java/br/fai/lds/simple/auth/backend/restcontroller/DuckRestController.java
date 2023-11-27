package br.fai.lds.simple.auth.backend.restcontroller;

import br.fai.lds.domain.dto.DuckDto;
import br.fai.lds.simple.auth.backend.configuration.DuckBackendConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/duck")
public class DuckRestController {

    private final DuckBackendConfiguration duckBackendConfiguration;

    public DuckRestController(DuckBackendConfiguration duckBackendConfiguration) {
        this.duckBackendConfiguration = duckBackendConfiguration;
    }


    @GetMapping("/random")
    public DuckDto getRandomDuck() {
        return duckBackendConfiguration.randomDuckUseCase().getRandomDuck();
    }
}
