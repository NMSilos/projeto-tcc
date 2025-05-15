package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.ErroDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.CadastroUsuarioDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.LoginDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.TokenDTO;
import com.github.nmsilos.backendtcc.mapper.usuarios.CadastroUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody CadastroUsuarioDTO dados) {
        Usuario usuario = CadastroUsuarioMapper.toModel(dados);
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDTO dados) {
        //Tentativa de autenticação
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getUsername(), dados.getPassword());
        try{
            var authentication = manager.authenticate(authenticationToken);
            Usuario usuario = (Usuario) authentication.getPrincipal();
            String token = new TokenManager().generateToken(usuario);
            TokenDTO dto = new TokenDTO(token);
            return ResponseEntity.ok(dto);
        } catch (AuthenticationException e) {
            ErroDTO erro = new ErroDTO("Usuário/Senha incorreta");
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(erro);
        }
    }

}
