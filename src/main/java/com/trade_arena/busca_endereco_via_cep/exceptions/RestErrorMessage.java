package com.trade_arena.busca_endereco_via_cep.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestErrorMessage {
    private String code;
    private String message;
}
