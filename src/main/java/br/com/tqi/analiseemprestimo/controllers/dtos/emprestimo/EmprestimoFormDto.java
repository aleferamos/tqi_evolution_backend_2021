package br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo;

import br.com.tqi.analiseemprestimo.util.Enums.Emprestimo.StatusEmprestimoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class EmprestimoFormDto {

    @NotBlank
    private String valor;

    @NotNull(message = "Data da primeira parcela está vazia!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataPrimeiraParcela;

    @NotNull(message = "Quantidade de parcelas nulo")
    private Long quantidadeParcelas;

    private StatusEmprestimoEnum status;
}
