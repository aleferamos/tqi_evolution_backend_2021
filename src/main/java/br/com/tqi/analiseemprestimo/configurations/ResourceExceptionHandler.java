package br.com.tqi.analiseemprestimo.configurations;

import br.com.tqi.analiseemprestimo.controllers.dtos.ErroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public ResourceExceptionHandler(MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDto> handle(MethodArgumentNotValidException exception) {
        List<ErroDto> erros = new ArrayList<>();
        List<FieldError> errosValidacao = exception.getBindingResult().getFieldErrors();

        errosValidacao.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro,  LocaleContextHolder.getLocale());
            ErroDto erroDTO= new ErroDto(mensagem, erro.getField());

            erros.add(erroDTO);
        });

        return erros;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErroDto handle(IllegalArgumentException exception) {
        String message = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
        return new ErroDto(message);
    }


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(br.com.tqi.analiseemprestimo.exceptions.RegistroNaoEncontradoException.class)
    public ErroDto handle(br.com.tqi.analiseemprestimo.exceptions.RegistroNaoEncontradoException exception) {
        String message = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
        return new ErroDto(message);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException.class)
    public ErroDto handle(br.com.tqi.analiseemprestimo.exceptions.RegraDeNegocioException exception) {
        String message = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
        return new ErroDto(message);
    }
}
