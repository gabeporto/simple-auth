package br.fai.lds.simple.auth.backend.configuration;

import br.fai.lds.backend.implementation.repository.LoginDao;
import br.fai.lds.backend.usecases.authentication.LoginUseCase;
import br.fai.lds.backend.usecases.port.LoginRepository;
import br.fai.lds.domain.port.JwtService;
import br.fai.lds.simple.auth.backend.port.impl.JwtServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationBackendConfiguration {

    @Bean
    public LoginUseCase loginUseCase() {
        final LoginRepository loginRepository = new LoginDao();
        final JwtService jwtService = new JwtServiceImpl();
        return new LoginUseCase(loginRepository, jwtService);
    }
}
