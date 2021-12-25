package br.com.tqi.analiseemprestimo.controllers.dtos.cliente;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClienteDto {

    public ClienteDto(Long id, String nome, String email, String cpf, String rg, String renda, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.renda = renda;
        this.senha = senha;
    }

    public ClienteDto() {
    }

    private Long id;


    private String nome;

    private String email;

    private String cpf;

    private String rg;

    private String renda;

    private String senha;


}
