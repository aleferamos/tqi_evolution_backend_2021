package br.com.tqi.analiseemprestimo.controllers;

import br.com.tqi.analiseemprestimo.controllers.dtos.CadastrarCliente.CadastrarClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.CadastrarCliente.CadastrarClienteFormDto;
import br.com.tqi.analiseemprestimo.services.CadastrarClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("cadastro-cliente")
public class CadastrarClienteController {

    private CadastrarClienteService cadastrarClienteService;

    @Autowired
    public CadastrarClienteController(CadastrarClienteService cadastrarClienteService) {
        this.cadastrarClienteService = cadastrarClienteService;
    }

    @PostMapping
    public ResponseEntity<CadastrarClienteDto> salvar(@RequestBody CadastrarClienteFormDto cadastrarClienteFormDto){
        return ResponseEntity.ok(cadastrarClienteService.save(cadastrarClienteFormDto));
    }
}
