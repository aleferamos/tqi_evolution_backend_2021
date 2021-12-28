package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoFormDto;
import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import br.com.tqi.analiseemprestimo.models.Cliente;
import br.com.tqi.analiseemprestimo.models.Emprestimo;
import br.com.tqi.analiseemprestimo.repositories.EmprestimoRepository;
import br.com.tqi.analiseemprestimo.util.Enums.Emprestimo.StatusEmprestimoEnum;
import br.com.tqi.analiseemprestimo.util.Funcao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private ClienteService clienteService;
    private ModelMapper modelMapper;
    private Funcao funcao;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteService clienteService,
                             ModelMapper modelMapper, Funcao funcao) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
        this.funcao = funcao;
    }

    public Page<EmprestimoDto> listarEmprestimos(Pageable pageable, Long idCliente){
        funcao.verificarCliente(idCliente);
        ClienteDto clienteAutenticado = funcao.obterClienteAutenticado();
        funcao.RestringirRequisicao(idCliente, clienteAutenticado.getId(), "emprestimo.buscaRestritaTodos");
        Page<EmprestimoDto> emprestimos = emprestimoRepository.getAll(pageable, idCliente);
        funcao.verficarDadosPage(emprestimos, "emprestimo.naoEncontrado");
        return emprestimos;
    }

    public EmprestimoDto buscarEmprestimo(Long idCliente, Long id){
        funcao.verificarCliente(idCliente);
        ClienteDto clienteAutenticado = funcao.obterClienteAutenticado();
        funcao.RestringirRequisicao(idCliente, clienteAutenticado.getId(), "emprestimo.BuscaRestrita");
        EmprestimoDto emprestimo = emprestimoRepository.buscar(idCliente, id);
        funcao.verficarEmprestimo(id, "emprestimo.naoExist");
        return emprestimo;
    }

    public EmprestimoDto save(EmprestimoFormDto emprestimoFormDto, Long idCliente){
        ClienteDto clienteAutenticado = funcao.obterClienteAutenticado();
        funcao.RestringirRequisicao(idCliente, clienteAutenticado.getId(), "emprestimo.restringirSolicitacao");
        Cliente cliente = modelMapper.map(clienteService.getById(idCliente), Cliente.class);
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
