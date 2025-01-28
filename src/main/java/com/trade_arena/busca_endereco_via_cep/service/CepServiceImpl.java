package com.trade_arena.busca_endereco_via_cep.service;

import com.trade_arena.busca_endereco_via_cep.dto.ViaCepResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepServiceImpl implements CepService {

    private static final String ENDERECO = "https://viacep.com.br/ws/%s/json/";

    @Autowired
    private RestTemplate restTemplate;

    public ViaCepResponseDto buscarCep(String cep) {
        String url = String.format(ENDERECO, cep);
        return restTemplate.getForObject(url, ViaCepResponseDto.class);
    }


}
