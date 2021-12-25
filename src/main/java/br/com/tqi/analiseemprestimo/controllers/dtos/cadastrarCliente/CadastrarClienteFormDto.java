package br.com.tqi.analiseemprestimo.controllers.dtos.cadastrarCliente;

import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteFormDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoFormDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CadastrarClienteFormDto {

    private ClienteFormDto cliente;
    private EnderecoFormDto endereco;
}
