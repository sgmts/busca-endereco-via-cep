package com.trade_arena.busca_endereco_via_cep.service;

import com.trade_arena.busca_endereco_via_cep.dto.ViaCepResponseDto;
import com.trade_arena.busca_endereco_via_cep.exceptions.CepInvalidoException;
import com.trade_arena.busca_endereco_via_cep.exceptions.CepNaoEncontradoException;
import com.trade_arena.busca_endereco_via_cep.exceptions.ErroConsultaCepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

@Service
public class CepServiceImpl implements CepService {

    private static final String ENDERECO = "https://viacep.com.br/ws/%s/json/";
    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-?\\d{3}");


    @Autowired
    private RestTemplate restTemplate;

    public ViaCepResponseDto buscarCep(String cep) {

        if (!isCepValido(cep)){
            throw new CepInvalidoException();
        }

        try {
            String url = String.format(ENDERECO, cep);
            ViaCepResponseDto response = restTemplate.getForObject(url, ViaCepResponseDto.class);

            // Verifica se o CEP foi encontrado
            if (response.getErro().equals("true") || response.getCep() == null) {
                throw new CepNaoEncontradoException();
            }

        return response;
        } catch (CepNaoEncontradoException e) {
            // Re-lança a exceção, para ser tratada no nível superior
            throw e;

        } catch (Exception e) {
            throw new ErroConsultaCepException();
        }


    }
    private boolean isCepValido(String cep) {
        return CEP_PATTERN.matcher(cep).matches();
    }
}
