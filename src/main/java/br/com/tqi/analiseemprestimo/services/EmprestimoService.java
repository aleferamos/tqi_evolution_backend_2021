package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoFormDto;
import br.com.tqi.analiseemprestimo.models.Cliente;
import br.com.tqi.analiseemprestimo.models.Emprestimo;
import br.com.tqi.analiseemprestimo.repositories.EmprestimoRepository;
import br.com.tqi.analiseemprestimo.util.Enums.Emprestimo.StatusEmprestimoEnum;
import br.com.tqi.analiseemprestimo.util.Funcao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private ModelMapper modelMapper;
    private Funcao funcao;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             ModelMapper modelMapper, Funcao funcao) {
        this.emprestimoRepository = emprestimoRepository;
        this.modelMapper = modelMapper;
        this.funcao = funcao;
    }


    public Page<EmprestimoDto> listarEmprestimos(Pageable pageable){
        ClienteDto clienteAutenticado = funcao.obterClienteAutenticado();
        Page<EmprestimoDto> emprestimos = emprestimoRepository.getAll(pageable, clienteAutenticado.getId());
        funcao.verficarDadosPage(emprestimos, "emprestimo.naoEncontrado");
        return emprestimos;
    }

    public EmprestimoDto buscarEmprestimo(Long id){
        ClienteDto clienteAutenticado = funcao.obterClienteAutenticado();
        EmprestimoDto emprestimo = emprestimoRepository.buscar(clienteAutenticado.getId(), id);
        funcao.verficarEmprestimo(emprestimo, "emprestimo.naoExist");
        return emprestimo;
    }

    public EmprestimoDto save(EmprestimoFormDto emprestimoFormDto){
        ClienteDto clienteAutenticado = funcao.obterClienteAutenticado();
        Cliente cliente = modelMapper.map(clienteAutenticado, Cliente.class);
        Emprestimo emprestimo = modelMapper.map(emprestimoFormDto, Emprestimo.class);

        emprestimo.setEmissao(LocalDate.now());

        funcao.verificarQuantidadeParcelas(emprestimo.getQuantidadeParcelas());
        funcao.verificarData(emprestimo.getEmissao(), emprestimo.getDataPrimeiraParcela());
        emprestimo.setStatus(StatusEmprestimoEnum.ANALISE);
        emprestimo.setCliente(cliente);

        EmprestimoDto emprestimoDto = modelMapper.map(emprestimoRepository.save(emprestimo), EmprestimoDto.class);
        return emprestimoDto;
    }



}
