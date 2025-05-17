package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.usuarios.*;
import com.github.nmsilos.backendtcc.mapper.usuarios.CadastroUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.security.Cripter;
import com.github.nmsilos.backendtcc.security.TokenManager;
import com.github.nmsilos.backendtcc.service.UsuarioService;
import com.github.nmsilos.backendtcc.utils.GeradorSenhaAleatoria;
import com.github.nmsilos.backendtcc.utils.GoogleTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @PostMapping("cadastrar")
    public ResponseEntity<RespostaUsuarioDTO> cadastrarUsuario(@RequestBody CadastroUsuarioDTO dados) {
        Usuario usuario = CadastroUsuarioMapper.toModel(dados);
        RespostaUsuarioDTO resposta = service.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDTO dados) {
        TokenDTO token = service.login(dados);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest request) {
        TokenDTO token = service.loginComGoogle(request);
        return ResponseEntity.ok().body(token);
    }

}
