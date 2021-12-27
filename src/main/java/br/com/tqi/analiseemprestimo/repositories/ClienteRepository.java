package br.com.tqi.analiseemprestimo.repositories;

import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteFormDto;
import br.com.tqi.analiseemprestimo.models.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT NEW br.com.tqi.analiseemprestimo.controllers.dtos.cliente.ClienteDto" +
            "(c.id, c.nome, c.email, c.cpf, c.rg, c.renda, c.senha) " +
            "FROM Cliente c")
    Page<ClienteDto> getAll(Pageable pageable);


    Optional<Cliente> findById(Long id);

    @Query("SELECT DISTINCT CASE " +
            "WHEN COUNT(cliente) > 0 THEN true " +
            "ELSE false " +
            "END " +
            "FROM Cliente cliente " +
            "WHERE (cliente.email = :email) " +
            "AND (cliente.id <> :id OR :id IS NULL)")
    Boolean existEmail(@Param(value = "email") String email, @Param(value="id") Long id);

    public Optional<Cliente> findByEmail(String email);
}
