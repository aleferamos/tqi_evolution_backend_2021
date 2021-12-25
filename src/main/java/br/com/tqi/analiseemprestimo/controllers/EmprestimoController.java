package br.com.tqi.analiseemprestimo.controllers;

import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.emprestimo.EmprestimoFormDto;
import br.com.tqi.analiseemprestimo.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cliente/{idCliente}/emprestimo")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

//    @GetMapping("/todos")
//    public ResponseEntity<Page<EmprestimoDto>> listar(Pageable pageable){
//        return ResponseEntity.ok(emprestimoService.listarEmprestimos(pageable));
//    }

    @PostMapping("/novo")
    public ResponseEntity<EmprestimoDto> salvar(@RequestBody @Valid EmprestimoFormDto emprestimoFormDto, @PathVariable(value = "idCliente") Long idCliente){
        return ResponseEntity.ok(emprestimoService.save(emprestimoFormDto, idCliente));
    }
}
