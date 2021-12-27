package br.com.tqi.analiseemprestimo.controllers;

import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoFormDto;
import br.com.tqi.analiseemprestimo.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("cliente/{idCliente}/emprestimo")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<EmprestimoDto>> listarEmprestimos(Pageable pageable, @PathVariable(value = "idCliente") Long idCliente){
        return ResponseEntity.ok(emprestimoService.listarEmprestimos(pageable, idCliente));
    }

    @PostMapping("/novo")
    public ResponseEntity<Long> solicitar(@RequestBody @Valid EmprestimoFormDto emprestimoFormDto, @PathVariable(value = "idCliente") Long idCliente){
        EmprestimoDto emprestimo = emprestimoService.save(emprestimoFormDto, idCliente);
        URI uri = UriComponentsBuilder.fromPath("/novo").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimo.getId());
    }

    @GetMapping("/detalhe/{codigoEmprestimo}")
    public ResponseEntity<EmprestimoDto> buscarDetalhes(@PathVariable(value = "idCliente") Long idCliente, @PathVariable(value = "codigoEmprestimo") Long idEmprestimo){
        return ResponseEntity.ok(emprestimoService.buscarEmprestimo(idCliente, idEmprestimo));
    }
}
