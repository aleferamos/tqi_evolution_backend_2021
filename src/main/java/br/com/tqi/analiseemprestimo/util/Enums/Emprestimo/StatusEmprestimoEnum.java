package br.com.tqi.analiseemprestimo.util.Enums.Emprestimo;

public enum StatusEmprestimoEnum {

    ANALISE("Analise"),
    APROVADO("Aprovado"),
    REPROVADO("Reprovado"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusEmprestimoEnum(String descricao) {
        this.descricao = descricao;
    }
}
