package com.trade_arena.busca_endereco_via_cep.controller;

import com.trade_arena.busca_endereco_via_cep.dto.ViaCepResponseDto;
import com.trade_arena.busca_endereco_via_cep.service.CepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Tag(name = "Buscador de Endereco", description = "Realiza Buscas De Enderecos pelo CEP")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/buscar-cep/{cep}")
    @Operation(summary = "Busca endereco pelo CEP", description = "Endpoint responsável por consultar endereco pelo CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Endereço encontrado com sucesso."),
            @ApiResponse(responseCode = "400",description = "CEP inválido."),
            @ApiResponse(responseCode = "500",description = "Erro Interno do servidor."),
    })

    public ResponseEntity<ViaCepResponseDto> buscarCep(@PathVariable String cep) {
        return ResponseEntity.status(HttpStatus.OK).body(cepService.buscarCep(cep));
    }


}
