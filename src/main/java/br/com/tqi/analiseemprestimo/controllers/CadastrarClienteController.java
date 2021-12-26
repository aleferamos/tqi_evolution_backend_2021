package br.com.tqi.analiseemprestimo.controllers;

import br.com.tqi.analiseemprestimo.controllers.dtos.cadastrarCliente.CadastrarClienteDto;
import br.com.tqi.analiseemprestimo.controllers.dtos.cadastrarCliente.CadastrarClienteFormDto;
import br.com.tqi.analiseemprestimo.services.CadastrarClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiOperation(value = "Cadastra um cliente")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cadastra um cliente"),
        @ApiResponse(code = 400, message = "Email já existe no sistema!")
})
@RequestMapping(value = "cadastro-cliente", consumes="application/json", produces="application/json")
public class CadastrarClienteController {

    private CadastrarClienteService cadastrarClienteService;

    @Autowired
    public CadastrarClienteController(CadastrarClienteService cadastrarClienteService) {
        this.cadastrarClienteService = cadastrarClienteService;
    }

    @PostMapping
    public ResponseEntity<CadastrarClienteDto> cadastrar(@RequestBody CadastrarClienteFormDto cadastrarClienteFormDto){
        return ResponseEntity.ok(cadastrarClienteService.save(cadastrarClienteFormDto));
    }
}
