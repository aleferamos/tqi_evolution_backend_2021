package br.com.tqi.analiseemprestimo.controllers.dtos.endereco;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EnderecoFormDto {

    @NotBlank
    private String apelido;

    @NotBlank
    private String rua;

    @NotBlank
    private String numero;

    @NotBlank
    private String cep;
}
