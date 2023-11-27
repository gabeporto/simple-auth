package br.fai.lds.frontend.usecases.account;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.AccountRestService;

public class AccountUseCase {

    private final AccountRestService accountRestService;

    public AccountUseCase(AccountRestService accountRestService) {
        this.accountRestService = accountRestService;
    }

    public UserModel login(final String email, final String password) {

        if(email == null || !email.contains("@")) {
            return null;
        }

        if(password == null || password.length() < 2) {
            return null;
        }

        final String resource = "/";
        return accountRestService.validateCredentials(email, password);
    }
}
