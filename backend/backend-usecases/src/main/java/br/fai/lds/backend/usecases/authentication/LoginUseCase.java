package br.fai.lds.backend.usecases.authentication;

import br.fai.lds.backend.usecases.port.LoginRepository;
import br.fai.lds.domain.UserModel;
import br.fai.lds.domain.port.JwtService;

public class LoginUseCase {

    private final LoginRepository loginRepository;
    private final JwtService jwtService;

    public LoginUseCase(LoginRepository loginRepository, JwtService jwtService) {
        this.loginRepository = loginRepository;
        this.jwtService = jwtService;
    }

    public UserModel login(final String email, final String password) {
        if(email.isEmpty() || password.isEmpty()) {
            return null;
        }

        UserModel user = loginRepository.login(email, password);
        if(user == null) return null;

        user.setPassword("");

        final String token = jwtService.getJwtToken(user);

        if(token.equals("INVALID_TOKEN")) return null;

        user.setToken(token);

        return user;
    }
}
