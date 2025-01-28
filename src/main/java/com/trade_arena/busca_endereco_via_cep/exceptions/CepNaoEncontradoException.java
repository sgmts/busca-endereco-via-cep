package com.trade_arena.busca_endereco_via_cep.exceptions;

public class CepNaoEncontradoException extends RuntimeException {
  public CepNaoEncontradoException(String message) {
    super(message);
  }

  public CepNaoEncontradoException() {
  }
}
