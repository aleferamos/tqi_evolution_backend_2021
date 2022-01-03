package br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo;

import br.com.tqi.analiseemprestimo.models.Cliente;
import br.com.tqi.analiseemprestimo.util.Enums.Emprestimo.StatusEmprestimoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmprestimoDto {

    private Long id;

    private Long codigoEmprestimo;

    private String valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate emissao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPrimeiraParcela;

    private Long quantidadeParcelas;

    private StatusEmprestimoEnum status;

    private Cliente cliente;

    private String email;

    private String renda;

    public EmprestimoDto() {
    }

    public EmprestimoDto(Long id, LocalDate emissao, LocalDate dataPrimeiraParcela, Long quantidadeParcelas,
                         StatusEmprestimoEnum status,Cliente cliente) {
        this.id = id;
        this.emissao = emissao;
        this.dataPrimeiraParcela = dataPrimeiraParcela;
        this.quantidadeParcelas = quantidadeParcelas;
        this.status = status;
        this.cliente = cliente;
    }

    public EmprestimoDto(Long id, String valor, Long quantidadeParcelas, StatusEmprestimoEnum status) {
        this.codigoEmprestimo = id;
        this.valor = valor;
        this.quantidadeParcelas = quantidadeParcelas;
        this.status = status;
    }

    public EmprestimoDto(Long id, String valor, Long quantidadeParcelas, LocalDate dataPrimeiraParcela,
                         StatusEmprestimoEnum status ,Cliente cliente) {
        this.codigoEmprestimo = id;
        this.valor = valor;
        this.quantidadeParcelas = quantidadeParcelas;
        this.dataPrimeiraParcela = dataPrimeiraParcela;
        this.email = cliente.getEmail();
        this.renda = cliente.getRenda();
        this.status = status;
    }
}
