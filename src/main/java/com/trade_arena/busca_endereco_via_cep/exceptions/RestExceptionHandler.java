package com.trade_arena.busca_endereco_via_cep.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CEP_INVALIDO = "CEP inválido. O formato esperado é '00000-000' ou '00000000";
    private static final String CEP_NAO_ENCONTRADO = "CEP não encontrado";
    private static final String ERRO_DEFAULT = "Erro Interno!";

    private static final String CODE_1001 = "1001";
    private static final String CODE_1002 = "1002";
    private static final String CODE_2001 = "2001";

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<RestErrorMessage> cepInvalidoExceptionHandler(CepInvalidoException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(CODE_1001,CEP_INVALIDO);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(CepNaoEncontradoException.class)
    public ResponseEntity<RestErrorMessage> CepNaoEncontradoExceptionHandler( CepNaoEncontradoException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(CODE_1002,CEP_NAO_ENCONTRADO);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }

    @ExceptionHandler(ErroConsultaCepException.class)
    public ResponseEntity<RestErrorMessage> erroConsultaCepExceptionHandler( ErroConsultaCepException e) {
        RestErrorMessage threatResponse = new RestErrorMessage(CODE_2001,ERRO_DEFAULT);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResponse);
    }
}
