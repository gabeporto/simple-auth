package br.fai.lds.simple.auth.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "account/login";
    }
}
