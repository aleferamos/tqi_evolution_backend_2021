package br.com.tqi.analiseemprestimo.repositories;

import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.models.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT NEW br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto" +
            "(e.id, e.emissao, e.dataPrimeiraParcela, e.quantidadeParcelas, e.status, e.cliente) FROM Emprestimo e")
    Page<EmprestimoDto> getAll (Pageable pageable);
}
