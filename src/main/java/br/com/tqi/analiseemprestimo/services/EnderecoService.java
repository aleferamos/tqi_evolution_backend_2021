package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.endereco.EnderecoFormDto;
import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import br.com.tqi.analiseemprestimo.models.Endereco;
import br.com.tqi.analiseemprestimo.repositories.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }

    public EnderecoDto save(EnderecoFormDto enderecoFormDto){
        if(isEmpty(enderecoFormDto) == true){
            throw new RegraDeNegocioException("cliente.campoVazio");
        }
        Endereco endereco = modelMapper.map(enderecoFormDto, Endereco.class);
        EnderecoDto enderecoDto = modelMapper.map(enderecoRepository.save(endereco), EnderecoDto.class);
        return enderecoDto;
    }

    Boolean  isEmpty (EnderecoFormDto enderecoFormDto){
        Boolean isEmpty = false;
        if(enderecoFormDto.getApelido().isEmpty()
           || enderecoFormDto.getRua().isEmpty()
           || enderecoFormDto.getNumero().isEmpty()
           || enderecoFormDto.getCep().isEmpty()){
            isEmpty = true;
        }
        return isEmpty;
    }
}
