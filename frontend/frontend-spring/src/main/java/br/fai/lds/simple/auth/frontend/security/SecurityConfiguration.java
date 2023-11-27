package br.fai.lds.simple.auth.frontend.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .usernameParameter("email")
                .loginProcessingUrl("/authentication")
                .defaultSuccessUrl("/home")
                .failureHandler((request, response, exception) -> {
                    final FlashMap flashMap = new FlashMap();
                    flashMap.put("loginErrorMessage", "Email e/ou senha inválidos.");

                    final FlashMapManager flashMapManager = new SessionFlashMapManager();
                    flashMapManager.saveOutputFlashMap(flashMap, request, response);
                    response.sendRedirect("/account/login");
                })
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login");
    }
}
