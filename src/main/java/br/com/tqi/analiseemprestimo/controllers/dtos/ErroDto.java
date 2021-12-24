package br.com.tqi.analiseemprestimo.controllers.dtos;

import lombok.Getter;

@Getter
public class ErroDto {

    private String erro;
    private String propriedade;

    public ErroDto(String erro, String propriedade) {
        super();
        this.erro = erro;
        this.propriedade = propriedade;
    }

    public ErroDto(String erro) {
        this.erro = erro;
    }
}
