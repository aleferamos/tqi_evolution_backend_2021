package br.com.tqi.analiseemprestimo.repositories;

import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.models.Emprestimo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT NEW br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto" +
            "(e.id, e.valor, e.quantidadeParcelas, e.status) FROM Emprestimo e " +
            " WHERE e.cliente.id = :idCliente")
    Page<EmprestimoDto> getAll (Pageable pageable, Long idCliente);

    @Query("SELECT NEW br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto" +
            "(e.id, e.valor, e.quantidadeParcelas, e.dataPrimeiraParcela, e.status, e.cliente) FROM Emprestimo e " +
            " WHERE e.cliente.id = :idCliente AND e.id = :idEmprestimo")
    EmprestimoDto buscar (Long idCliente, Long idEmprestimo);
}
