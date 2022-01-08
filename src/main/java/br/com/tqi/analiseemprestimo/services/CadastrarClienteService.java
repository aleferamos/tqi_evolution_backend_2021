package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.controllers.dtos.cadastrarCliente.CadastrarClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cadastrarCliente.CadastrarClienteFormDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteFormDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoFormDto;
import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastrarClienteService {

    private ClienteService clienteService;
    private EnderecoService enderecoService;
    private ModelMapper modelMapper;

    @Autowired
    public CadastrarClienteService(ClienteService clienteService, EnderecoService enderecoService,
                                   ModelMapper modelMapper) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CadastrarClienteDto save(CadastrarClienteFormDto cadastrarClienteFormDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(isEmpty(cadastrarClienteFormDto)){
            throw new RegraDeNegocioException("cliente.campoVazio");
        }
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

    Boolean isEmpty (CadastrarClienteFormDto cadastrarClienteFormDto){
        boolean isEmpty = false;
        if(cadastrarClienteFormDto.getCliente().getNome().isBlank()
                || cadastrarClienteFormDto.getCliente().getEmail().isBlank()
                || cadastrarClienteFormDto.getCliente().getCpf().isBlank()
                || cadastrarClienteFormDto.getCliente().getRg().isBlank()
                || cadastrarClienteFormDto.getCliente().getRenda().isBlank()
                || cadastrarClienteFormDto.getEndereco().getApelido().isBlank()
                || cadastrarClienteFormDto.getEndereco().getRua().isBlank()
                || cadastrarClienteFormDto.getEndereco().getNumero().isBlank()
                || cadastrarClienteFormDto.getEndereco().getCep().isBlank()
        ){
            isEmpty = true;
        }
        return isEmpty;
    }
}
