package br.com.tqi.analiseemprestimo.controllers.dtos.cliente;

import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClienteFormDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String cpf;

    @NotBlank
    private String rg;

    @NotBlank
    private String renda;

    @NotBlank
    private String senha;

    private EnderecoDto endereco;
}
