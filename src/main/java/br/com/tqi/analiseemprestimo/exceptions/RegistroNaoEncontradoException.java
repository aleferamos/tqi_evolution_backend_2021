package br.com.tqi.analiseemprestimo.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException{

    public RegistroNaoEncontradoException() {
        super("erro.naoEncontrado");
    }

    public RegistroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
