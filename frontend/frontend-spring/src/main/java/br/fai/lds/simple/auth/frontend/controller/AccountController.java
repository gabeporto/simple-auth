package br.fai.lds.simple.auth.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "account/login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam("email") final String email,
                                   @RequestParam("password") final String password) {

        System.out.println(email + password);
        return "redirect:login";
    }
}
