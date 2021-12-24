package br.com.tqi.analiseemprestimo.controllers.dtos.CadastrarCliente;

import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarClienteDto {

    private ClienteDto cliente;

    private EnderecoDto endereco;
}
