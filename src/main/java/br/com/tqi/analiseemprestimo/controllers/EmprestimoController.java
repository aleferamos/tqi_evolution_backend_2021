package br.com.tqi.analiseemprestimo.controllers;

import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoFormDto;
import br.com.tqi.analiseemprestimo.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("emprestimo")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<EmprestimoDto>> listarEmprestimos(@PageableDefault(sort = {"emissao"}) Pageable pageable
    ){
        return ResponseEntity.ok(emprestimoService.listarEmprestimos(pageable));
    }

    @PostMapping("/novo")
    public ResponseEntity<Long> solicitar(@RequestBody @Valid EmprestimoFormDto emprestimoFormDto){
        EmprestimoDto emprestimo = emprestimoService.save(emprestimoFormDto);
        URI uri = UriComponentsBuilder.fromPath("/novo").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(emprestimo.getId());
    }

    @GetMapping("/detalhe/{codigoEmprestimo}")
    public ResponseEntity<EmprestimoDto> buscarDetalhes(@PathVariable(value = "codigoEmprestimo") Long idEmprestimo){
        return ResponseEntity.ok(emprestimoService.buscarEmprestimo(idEmprestimo));
    }
}
