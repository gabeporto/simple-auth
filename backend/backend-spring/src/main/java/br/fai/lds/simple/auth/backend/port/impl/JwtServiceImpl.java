package br.fai.lds.simple.auth.backend.port.impl;

import br.fai.lds.domain.UserModel;
import br.fai.lds.domain.port.JwtService;
import br.fai.lds.simple.auth.backend.security.APISecurityConstants;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtServiceImpl implements JwtService {

    @Override
    public String getJwtToken(UserModel user) {

        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(("ROLE_USER")));

        try {
            final String token = Jwts.builder()
                    .setId("SIMPLE_AUTH")
                    .setSubject(user.getEmail())
                    .claim(APISecurityConstants.AUTHORITIES, grantedAuthorities.stream().map(
                                    GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + convertTime(20)))
                    .signWith(APISecurityConstants.KEY)
                    .compact();

            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int convertTime(final int minutes) {
        return minutes * 60 * 100;
    }
}
