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

    private String valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate emissao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPrimeiraParcela;

    private Long quantidadeParcelas;

    private StatusEmprestimoEnum status;

    private Cliente cliente;

    public EmprestimoDto() {
    }

    public EmprestimoDto(Long id, LocalDate emissao, LocalDate dataPrimeiraParcela, Long quantidadeParcelas, StatusEmprestimoEnum status, Cliente cliente) {
        this.id = id;
        this.emissao = emissao;
        this.dataPrimeiraParcela = dataPrimeiraParcela;
        this.quantidadeParcelas = quantidadeParcelas;
        this.status = status;
        this.cliente = cliente;
    }
}
