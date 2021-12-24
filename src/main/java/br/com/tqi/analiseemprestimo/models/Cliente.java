package br.com.tqi.analiseemprestimo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "renda")
    private String renda;

    @Column(name = "senha")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "endereco_id", foreignKey = @ForeignKey(name = "cliente_endereco"))
    private Endereco endereco;
}
