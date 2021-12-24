package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.controllers.dtos.CadastrarCliente.CadastrarClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.CadastrarCliente.CadastrarClienteFormDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteFormDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoFormDto;
import br.com.tqi.analiseemprestimo.models.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
public class CadastrarClienteService {

    private ClienteService clienteService;
    private EnderecoService enderecoService;
    private ModelMapper modelMapper;

    @Autowired
    public CadastrarClienteService(ClienteService clienteService, EnderecoService enderecoService, ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CadastrarClienteDto save(@Valid CadastrarClienteFormDto cadastrarClienteFormDto){

        CadastrarClienteDto cadastrarCliente = new CadastrarClienteDto();

        EnderecoFormDto endereco = cadastrarClienteFormDto.getEndereco();
        EnderecoDto enderecoSalvar = enderecoService.save(endereco);

        ClienteFormDto cliente = cadastrarClienteFormDto.getCliente();
        cliente.setEndereco(enderecoSalvar);
        ClienteDto clienteSalvar = modelMapper.map(clienteService.save(cliente), ClienteDto.class);

        cadastrarCliente.setEndereco(enderecoSalvar);
        cadastrarCliente.setCliente(clienteSalvar);

        return cadastrarCliente;

    }
}
