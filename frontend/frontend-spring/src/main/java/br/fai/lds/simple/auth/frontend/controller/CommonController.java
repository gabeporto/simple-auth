package br.fai.lds.simple.auth.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/home")
    public String getHomePage() {
        return "common/home";
    }
}
