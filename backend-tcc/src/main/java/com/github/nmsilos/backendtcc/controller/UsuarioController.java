package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.usuarios.CadastroUsuarioDTO;
import com.github.nmsilos.backendtcc.mapper.usuarios.CadastroUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody CadastroUsuarioDTO dados) {
        Usuario usuario = CadastroUsuarioMapper.toModel(dados);
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

}
