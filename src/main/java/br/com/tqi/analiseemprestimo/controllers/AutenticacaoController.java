package br.com.tqi.analiseemprestimo.controllers;

import br.com.tqi.analiseemprestimo.controllers.dtos.TokenValidDto;
import br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException;
import br.com.tqi.analiseemprestimo.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    @Autowired
    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping(value = "/validar")
    public ResponseEntity<Boolean> tokenIsValid(@RequestBody(required = false) TokenValidDto token){
        try{

            if(token.getToken().isBlank() || token.getToken() == null){
                throw new RegraDeNegocioException("token.campoVazio");
            }
        }catch(NullPointerException npe){
            throw new RegraDeNegocioException("token.campoVazio");
        }


        Boolean tokenValid = autenticacaoService.isTokenValid(token.getToken());
        return ResponseEntity.ok(tokenValid);
    }
}
