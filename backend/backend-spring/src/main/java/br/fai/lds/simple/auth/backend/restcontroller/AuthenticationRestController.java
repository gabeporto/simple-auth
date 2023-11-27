package br.fai.lds.simple.auth.backend.restcontroller;

import br.fai.lds.backend.usecases.authentication.LoginUseCase;
import br.fai.lds.domain.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationRestController {

    public final LoginUseCase loginUseCase;

    public AuthenticationRestController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public UserModel authentication(@RequestHeader("email") final String email,
                                      @RequestHeader("password") final String password) {

        UserModel userModel = loginUseCase.login(email, password);
        return userModel;
    }
}
