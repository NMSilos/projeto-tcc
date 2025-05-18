package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.livros.CadastroLivroDTO;
import com.github.nmsilos.backendtcc.mapper.livros.CadastroLivroMapper;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<Livro> cadastrar(@RequestBody CadastroLivroDTO livro) {
        Livro novo = CadastroLivroMapper.toModel(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(novo));
    }

}
