package br.fai.lds.domain.port;

import br.fai.lds.domain.UserModel;

public interface JwtService {

    String getJwtToken(final UserModel user);
}
