package br.fai.lds.backend.usecases.port;

import br.fai.lds.domain.UserModel;

public interface LoginRepository {

    public UserModel login(final String email, final String password);

}
