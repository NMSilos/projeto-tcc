package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.livros.BuscarLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.CadastroLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.RespostaLivroNoListDTO;
import com.github.nmsilos.backendtcc.mapper.livros.CadastroLivroMapper;
import com.github.nmsilos.backendtcc.mapper.livros.RespostaLivroNoListMapper;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/buscar/{id}")
    public ResponseEntity<RespostaLivroNoListDTO> buscar(@AuthenticationPrincipal Admin usuario, @PathVariable Long id) {
        RespostaLivroNoListDTO livro = RespostaLivroNoListMapper.toNoListDTO(service.buscarInfo(id));
        return ResponseEntity.ok().body(livro);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<BuscarLivroDTO>> buscarPorTitulo(@AuthenticationPrincipal Admin usuario, @RequestParam("query") String query) {
        List<BuscarLivroDTO> livros = service.buscarPorTitulo(query);
        return ResponseEntity.ok().body(livros);
    }

}
