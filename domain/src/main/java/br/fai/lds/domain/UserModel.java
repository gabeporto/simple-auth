package br.fai.lds.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserModel {

    private int id;
    private String email;
    private String password;
    private String token;
}
