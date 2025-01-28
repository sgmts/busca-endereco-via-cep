package com.trade_arena.busca_endereco_via_cep.exceptions;

public class CepInvalidoException extends RuntimeException {
  public CepInvalidoException(String message) {
    super(message);
  }

  public CepInvalidoException() {
  }
}
