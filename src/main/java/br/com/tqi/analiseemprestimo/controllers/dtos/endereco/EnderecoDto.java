package br.com.tqi.analiseemprestimo.controllers.dtos.endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

    private Long id;

    private String apelido;

    private String rua;

    private String numero;

    private String cep;

    public EnderecoDto(String apelido, String rua, String numero, String cep) {
        this.apelido = apelido;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public EnderecoDto() {
    }
}
