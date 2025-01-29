package com.trade_arena.busca_endereco_via_cep.controller;

import com.trade_arena.busca_endereco_via_cep.dto.ViaCepResponseDto;
import com.trade_arena.busca_endereco_via_cep.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/buscar-cep/{cep}")
    public ResponseEntity<ViaCepResponseDto> buscarCep(@PathVariable String cep) {
        return ResponseEntity.status(HttpStatus.OK).body(cepService.buscarCep(cep));
    }
}
