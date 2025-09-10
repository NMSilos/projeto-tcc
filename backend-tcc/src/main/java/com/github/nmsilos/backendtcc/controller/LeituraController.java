package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.leituras.CadastroLeituraDTO;
import com.github.nmsilos.backendtcc.dto.leituras.EditarLeituraDTO;
import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.enums.StatusLeitura;
import com.github.nmsilos.backendtcc.mapper.leituras.RespostaLeituraMapper;
import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.service.LeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/buscar-leitura/{id}")
    public ResponseEntity<RespostaLeituraDTO> buscarLeitura(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id){
        RespostaLeituraDTO leitura = RespostaLeituraMapper.toDto(service.buscarInfo(id));
        return ResponseEntity.status(HttpStatus.OK).body(leitura);
    }

    @DeleteMapping("/excluir-por-livro/{userId}")
    public ResponseEntity<Void> excluirPorLivro(@AuthenticationPrincipal Usuario usuario, @PathVariable Long userId, @RequestBody Livro livro){
        service.excluir(usuario, userId, livro);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{username}/{status}")
    public ResponseEntity<List<RespostaLeituraDTO>> buscarPorStatus(
            @AuthenticationPrincipal Usuario usuario, @PathVariable String username, @PathVariable String status){
        StatusLeitura statusEnum = StatusLeitura.fromUrl(status);
        List<Leitura> leituras = service.buscarPorStatus(statusEnum, username);

        List<RespostaLeituraDTO> resposta = leituras.stream().map(RespostaLeituraMapper::toDto).toList();
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PutMapping("/editar")
    ResponseEntity<Void> editarLeitura(@RequestBody EditarLeituraDTO leitura){
        service.editarLeitura(leitura);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
