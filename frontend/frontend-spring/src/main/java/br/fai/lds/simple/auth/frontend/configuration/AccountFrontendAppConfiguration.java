package br.fai.lds.simple.auth.frontend.configuration;

import br.fai.lds.frontend.usecases.account.AccountUseCase;
import br.fai.lds.frontend.usecases.port.AccountRestService;
import br.fai.lds.simple.auth.frontend.port.impl.AccountRestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountFrontendAppConfiguration {

    @Bean
    public AccountUseCase accountUseCase() {
        final AccountRestService accountRestService = new AccountRestApiController();
        return new AccountUseCase(accountRestService);
    }
}
