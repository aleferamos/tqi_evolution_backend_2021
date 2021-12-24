package br.com.tqi.analiseemprestimo.repositories;

import br.com.tqi.analiseemprestimo.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
