package br.com.tqi.analiseemprestimo.controllers;

import br.com.tqi.analiseemprestimo.controllers.dtos.cadastrarCliente.CadastrarClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cadastrarCliente.CadastrarClienteFormDto;
import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import br.com.tqi.analiseemprestimo.services.CadastrarClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@ApiOperation(value = "Cadastra um cliente")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cadastra um cliente"),
        @ApiResponse(code = 400, message = "Email já existe no sistema!")
})
@RequestMapping(value = "cadastro-cliente", consumes="application/json", produces="application/json")
public class CadastrarClienteController {

    private CadastrarClienteService cadastrarClienteService;
    private PasswordEncoder encoder;

    @Autowired
    public CadastrarClienteController(CadastrarClienteService cadastrarClienteService, PasswordEncoder encoder) {
        this.cadastrarClienteService = cadastrarClienteService;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrar(@RequestBody CadastrarClienteFormDto cadastrarClienteFormDto){
        if(cadastrarClienteFormDto.getCliente().getSenha().isBlank()){
            throw new RegraDeNegocioException("cliente.campoVazio");
        }
        cadastrarClienteFormDto.getCliente().setSenha(encoder.encode(cadastrarClienteFormDto.getCliente().getSenha()));
        CadastrarClienteDto cliente = cadastrarClienteService.save(cadastrarClienteFormDto);
        URI uri = UriComponentsBuilder.fromPath("emails/{id}").buildAndExpand(cliente.getCliente().getId()).toUri();
        return ResponseEntity.created(uri).body(cliente.getCliente().getId());
    }
}
