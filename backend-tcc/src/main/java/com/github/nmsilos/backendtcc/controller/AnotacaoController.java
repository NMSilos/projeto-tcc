package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.anotacoes.CadastroAnotacaoDTO;
import com.github.nmsilos.backendtcc.dto.anotacoes.RespostaAnotacaoDTO;
import com.github.nmsilos.backendtcc.service.AnotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anotacoes")
public class AnotacaoController {

    @Autowired
    private AnotacaoService service;

    @PostMapping("/anotar")
    public ResponseEntity<RespostaAnotacaoDTO> anotar(@RequestBody CadastroAnotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<RespostaAnotacaoDTO> editar(@PathVariable Long id, @RequestBody CadastroAnotacaoDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editar(id, dto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
