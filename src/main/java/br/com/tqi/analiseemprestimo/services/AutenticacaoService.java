package br.com.tqi.analiseemprestimo.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Value("${jwt.secret}")
    private String secret;

    public boolean isTokenValid(String token) {

        if(token != null) {
            try {
                JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
                return true;
            }catch (Exception e) {
                return false;
            }

        }
        return false;
    }
}
