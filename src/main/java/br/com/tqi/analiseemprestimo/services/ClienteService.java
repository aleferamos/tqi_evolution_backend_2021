package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteFormDto;
import br.com.tqi.analiseemprestimo.models.Cliente;
import br.com.tqi.analiseemprestimo.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public ClienteDto save(ClienteFormDto clienteFormDto){
        Cliente cliente = modelMapper.map(clienteFormDto, Cliente.class);
        Boolean existEmail = clienteRepository.existEmail(cliente.getEmail(), cliente.getId());
        if(existEmail){
            throw new RegraDeNegocioException("cliente.EmailExist");
        }

        ClienteDto clienteDto = modelMapper.map(clienteRepository.save(cliente), ClienteDto.class);

        return clienteDto;
    }

    public ClienteDto getByEmail(String email){
        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(() -> new RegraDeNegocioException("cliente.naoEncontrado"));
        ClienteDto clienteConvert = modelMapper.map(cliente, ClienteDto.class);
        return clienteConvert;
    }
}
