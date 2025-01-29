package com.trade_arena.busca_endereco_via_cep.service;

import com.trade_arena.busca_endereco_via_cep.dto.ViaCepResponseDto;
import com.trade_arena.busca_endereco_via_cep.exceptions.CepInvalidoException;
import com.trade_arena.busca_endereco_via_cep.exceptions.CepNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CepServiceImplTest {

    @InjectMocks
    private CepServiceImpl cepService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() throws Exception {
        // Define o valor do campo urlViaCep usando reflexão
        Field urlViaCepField = CepServiceImpl.class.getDeclaredField("urlViaCep");
        urlViaCepField.setAccessible(true);
        urlViaCepField.set(cepService, "https://viacep.com.br/ws/%s/json/");
    }

    @Test
    void shouldThrowCepInvalidoExceptionWhenCepIsInvalid() {
        String invalidCep = "12345";

        Exception exception = assertThrows(CepInvalidoException.class, () -> {
            cepService.buscarCep(invalidCep);
        });
    }

    @Test
    void shouldReturnCepDataWhenApiReturnsValidResponse() {
        String validCep = "31150-200";
        ViaCepResponseDto mockResponse = new ViaCepResponseDto();
        mockResponse.setCep(validCep);
        mockResponse.setLogradouro("Praça da Sé");
        mockResponse.setBairro("Sé");
        mockResponse.setLocalidade("São Paulo");
        mockResponse.setUf("SP");

        when(restTemplate.getForObject(anyString(), eq(ViaCepResponseDto.class))).thenReturn(mockResponse);

        ViaCepResponseDto result = cepService.buscarCep(validCep);

        assertNotNull(result);
        assertEquals("Praça da Sé", result.getLogradouro());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(ViaCepResponseDto.class));
    }

    @Test
    void shouldThrowCepNaoEncontradoExceptionWhenApiReturnsNegative() {
        String validCep = "31950-559";
        ViaCepResponseDto mockResponse = new ViaCepResponseDto();
        mockResponse.setErro("true");

        when(restTemplate.getForObject(anyString(), eq(ViaCepResponseDto.class))).thenReturn(mockResponse);

        Exception exception = assertThrows(CepNaoEncontradoException.class, () -> {
            cepService.buscarCep(validCep);
        });
    }
}
