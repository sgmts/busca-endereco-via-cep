package com.trade_arena.busca_endereco_via_cep.service;

import com.trade_arena.busca_endereco_via_cep.dto.ViaCepResponseDto;

public interface CepService {

    ViaCepResponseDto buscarCep(String cep);
}
