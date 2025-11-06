package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.sugestoes.RespostaSugestaoDTO;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Sugestao;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.service.SugestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sugestoes")
public class SugestaoController {

    @Autowired
    private SugestaoService service;

    @PostMapping("cadastrar")
    ResponseEntity cadastrarSugestao(@AuthenticationPrincipal Usuario usuario, @RequestBody Sugestao sugestao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(sugestao));
    }

    @GetMapping("/buscar/all")
    ResponseEntity<List<RespostaSugestaoDTO>> buscarTodos(@AuthenticationPrincipal Admin usuario) {
        List<RespostaSugestaoDTO> sugestoes = service.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(sugestoes);
    }

    @PutMapping("/status/{id}")
    ResponseEntity<Void> alterarStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.alterarStatus(id));
    }

    @DeleteMapping("/deletar/{id}")
    ResponseEntity<Void> deletar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.deletar(id));
    }
}
