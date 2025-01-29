package com.trade_arena.busca_endereco_via_cep.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler handler;

    @Test
    void shouldHandleCepInvalidoException() {
        CepInvalidoException exception = new CepInvalidoException();
        ResponseEntity<RestErrorMessage> response = handler.cepInvalidoExceptionHandler(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CEP inválido. O formato esperado é '00000-000' ou '00000000", response.getBody().getMessage());
    }

    @Test
    void shouldHandleCepNaoEncontradoException() {
        CepNaoEncontradoException exception = new CepNaoEncontradoException();
        ResponseEntity<RestErrorMessage> response = handler.CepNaoEncontradoExceptionHandler(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("CEP não encontrado", response.getBody().getMessage());
    }

    @Test
    void shouldHandleerroConsultaCepException() {
        ErroConsultaCepException exception = new ErroConsultaCepException();
        ResponseEntity<RestErrorMessage> response = handler.erroConsultaCepExceptionHandler(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro Interno!", response.getBody().getMessage());
    }
}