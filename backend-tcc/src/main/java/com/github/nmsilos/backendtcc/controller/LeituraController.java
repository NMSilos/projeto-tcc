package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.leituras.CadastroLeituraDTO;
import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.service.LeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leituras")
public class LeituraController {

    @Autowired
    private LeituraService service;

    @PostMapping("/criar-leitura")
    public ResponseEntity<RespostaLeituraDTO> criarLeitura(
            @AuthenticationPrincipal Usuario usuario, @RequestBody CadastroLeituraDTO leitura) {
        RespostaLeituraDTO resposta = service.criarLeitura(usuario, leitura);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

}
