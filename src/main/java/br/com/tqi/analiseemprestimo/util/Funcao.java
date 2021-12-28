package br.com.tqi.analiseemprestimo.util;

import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import br.com.tqi.analiseemprestimo.repositories.EmprestimoRepository;
import br.com.tqi.analiseemprestimo.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Service
public class Funcao {

    private ModelMapper modelMapper;
    private ClienteService clienteService;
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    public Funcao(ModelMapper modelMapper, ClienteService clienteService, EmprestimoRepository emprestimoRepository) {
        this.modelMapper = modelMapper;
        this.clienteService = clienteService;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Boolean verificarData(LocalDate dataEmissao, LocalDate PrimeiraParcela){
        Calendar cal = Calendar.getInstance();
        Date date = modelMapper.map(dataEmissao, Date.class);

        cal.setTime(date);
        cal.add(Calendar.MONTH, 3);
        date = cal.getTime();

        LocalDate dataConvert = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDate();
        LocalDate dataPrimeiraParcela = PrimeiraParcela;

        if(dataPrimeiraParcela.isAfter(dataConvert)){
            throw new RegraDeNegocioException("parcela.dataPrimeiraParcela");
        }

        return false;
    }

    public Boolean verificarQuantidadeParcelas(Long quantidadeParcelas){
        if(quantidadeParcelas > 60){
            throw new RegraDeNegocioException("parcela.ultrapassada");
        }

        return false;
    }

    public Boolean verificarCliente(Long idCliente){
        clienteService.getById(idCliente);
        return false;
    }

    public Boolean verficarDadosPage(Page<?> dados, String mensagem){
        if(dados.getTotalElements() < 1){
            throw new RegraDeNegocioException(mensagem);
        }
        return false;
    }

    public Boolean verficarDadosObj(Object dados, String mensagem){
        if(dados ==  null){
            throw new RegraDeNegocioException(mensagem);
        }
        return false;
    }

    public Boolean verficarEmprestimo(Long id, String mensagem){
       var emprestimo =  emprestimoRepository.findById(id);
       if(emprestimo.isEmpty()){
           throw new RegraDeNegocioException(mensagem);
       }
        return false;
    }
}
