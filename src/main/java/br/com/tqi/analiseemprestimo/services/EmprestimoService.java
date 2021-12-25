package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoFormDto;
import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import br.com.tqi.analiseemprestimo.models.Cliente;
import br.com.tqi.analiseemprestimo.models.Emprestimo;
import br.com.tqi.analiseemprestimo.repositories.EmprestimoRepository;
import br.com.tqi.analiseemprestimo.util.Enums.Emprestimo.StatusEmprestimoEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private ClienteService clienteService;
    private ModelMapper modelMapper;
    private SimpleDateFormat simpleDateFormat;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteService clienteService, ModelMapper modelMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteService = clienteService;
        this.modelMapper = modelMapper;
    }

    public Page<EmprestimoDto> listarEmprestimos(Pageable pageable, Long idCliente){
        return emprestimoRepository.getAll(pageable);
    }

    public EmprestimoDto save(EmprestimoFormDto emprestimoFormDto, Long idCliente){
        Cliente cliente = modelMapper.map(clienteService.getById(idCliente), Cliente.class);
        Emprestimo emprestimo = modelMapper.map(emprestimoFormDto, Emprestimo.class);

        if(emprestimo.getQuantidadeParcelas() > 60){
            throw new RegraDeNegocioException("parcela.ultrapassada");
        }

        emprestimo.setEmissao(LocalDate.now());

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        Date date = modelMapper.map(emprestimo.getEmissao(), Date.class);

        cal.setTime(date);
        cal.add(Calendar.MONTH, 3);
        date = cal.getTime();

        LocalDate dataConvert = date.toInstant().atZone( ZoneId.systemDefault()).toLocalDate();
        LocalDate dataPrimeiraParcela = emprestimo.getDataPrimeiraParcela();

        if(dataPrimeiraParcela.isAfter(dataConvert)){
            throw new RegraDeNegocioException("parcela.dataPrimeiraParcela");
        }
        if(dataPrimeiraParcela.isBefore(dataConvert)){
            System.out.println(dataPrimeiraParcela + " é menor que: " + dataConvert);
        } else {
            System.out.println(dataPrimeiraParcela + " é maior que: " + dataConvert);
        }

        emprestimo.setStatus(StatusEmprestimoEnum.ANALISE);
        emprestimo.setCliente(cliente);

        EmprestimoDto emprestimoDto = modelMapper.map(emprestimoRepository.save(emprestimo), EmprestimoDto.class);
        return emprestimoDto;
    }


}
