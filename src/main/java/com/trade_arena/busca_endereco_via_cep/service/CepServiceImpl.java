package com.trade_arena.busca_endereco_via_cep.service;

import com.trade_arena.busca_endereco_via_cep.dto.ViaCepResponseDto;
import com.trade_arena.busca_endereco_via_cep.exceptions.CepInvalidoException;
import com.trade_arena.busca_endereco_via_cep.exceptions.CepNaoEncontradoException;
import com.trade_arena.busca_endereco_via_cep.exceptions.ErroConsultaCepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

@Service
public class CepServiceImpl implements CepService {

    @Value("${api.viacep.url}")
    private  String urlViaCep;

    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-?\\d{3}");
    private static final String  ERRO_RESPONSE_EXPLICITO = "true";


    @Autowired
    private RestTemplate restTemplate;

    public ViaCepResponseDto buscarCep(String cep) {

        if (!isCepValido(cep)){
            throw new CepInvalidoException();
        }

        try {
            // Monta a URL da requisição
            String url = String.format(urlViaCep, cep);
            // Faz a requisição para a API ViaCEP
            ViaCepResponseDto response = restTemplate.getForObject(url, ViaCepResponseDto.class);

            // Verifica se a resposta é nula ou se a API retornou erro explícito
            if (response == null || ERRO_RESPONSE_EXPLICITO.equals(response.getErro()) || response.getCep() == null) {
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
