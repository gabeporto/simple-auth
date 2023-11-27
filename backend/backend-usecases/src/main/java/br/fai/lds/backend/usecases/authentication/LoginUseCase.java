package br.fai.lds.backend.usecases.authentication;

import br.fai.lds.domain.UserModel;

public class LoginUseCase {

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
