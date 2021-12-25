package br.com.tqi.analiseemprestimo.models;

import br.com.tqi.analiseemprestimo.util.Enums.Emprestimo.StatusEmprestimoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "valor")
    private String valor;

    @Column(name = "emissao")
    private LocalDate emissao;

    @Column(name = "data_primeira_parcela")
    private LocalDate dataPrimeiraParcela;

    @Column(name = "quantidade_parcelas")
    private Long quantidadeParcelas;

    @Column(name = "status")
    private StatusEmprestimoEnum status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "emprestimo_cliente"))
    private Cliente cliente;
}
