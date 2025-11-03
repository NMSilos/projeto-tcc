package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.anotacoes.CadastroAnotacaoDTO;
import com.github.nmsilos.backendtcc.dto.anotacoes.RespostaAnotacaoDTO;
import com.github.nmsilos.backendtcc.service.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anotacoes")
public class AnotacaoController {

    @Autowired
    private AnotacaoService service;

    @Transactional
    @PostMapping("/anotar")
    public ResponseEntity<RespostaAnotacaoDTO> criarAnotacao(@RequestBody CadastroAnotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

}
