package br.fai.lds.simple.auth.frontend.controller;

import br.fai.lds.domain.dto.DuckDto;
import br.fai.lds.frontend.usecases.duck.RandomDuckUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {

    private final RandomDuckUseCase randomDuckUseCase;

    public CommonController(RandomDuckUseCase randomDuckUseCase) {
        this.randomDuckUseCase = randomDuckUseCase;
    }

    @GetMapping
    public String redirectToHomePage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage(final Model model) {

        DuckDto randomDuck = randomDuckUseCase.getRandomDuck();
        model.addAttribute("duck", randomDuck);

        return "common/home";
    }
}
