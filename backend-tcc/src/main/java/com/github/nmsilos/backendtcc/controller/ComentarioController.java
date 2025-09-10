package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.comentarios.CadastroComentarioDTO;
import com.github.nmsilos.backendtcc.dto.comentarios.RespostaComentarioDTO;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Comentario;
import com.github.nmsilos.backendtcc.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

/*
    @PostMapping("/comentar")
    public ResponseEntity<RespostaComentarioDTO> comentar(@AuthenticationPrincipal Admin usuario, @RequestBody Comentario comentario) {
        RespostaComentarioDTO resposta = service.comentar(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
    */

}
