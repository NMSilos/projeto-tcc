package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.model.Sugestao;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.service.SugestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sugestoes")
public class SugestaoController {

    @Autowired
    private SugestaoService service;

    @PostMapping("cadastrar")
    ResponseEntity cadastrarSugestao(@AuthenticationPrincipal Usuario usuario, @RequestBody Sugestao sugestao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(sugestao));
    }

}
