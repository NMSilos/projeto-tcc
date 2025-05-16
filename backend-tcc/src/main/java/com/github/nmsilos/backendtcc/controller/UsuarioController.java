package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.ErroDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.CadastroUsuarioDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.GoogleLoginRequest;
import com.github.nmsilos.backendtcc.dto.usuarios.LoginDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.TokenDTO;
import com.github.nmsilos.backendtcc.mapper.usuarios.CadastroUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.security.Cripter;
import com.github.nmsilos.backendtcc.security.TokenManager;
import com.github.nmsilos.backendtcc.utils.GeradorSenhaAleatoria;
import com.github.nmsilos.backendtcc.utils.GoogleTokenVerifier;
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
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

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

    @PostMapping("google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest request) {
        try {
            String idToken = request.getCredential();
            GoogleIdToken.Payload payload = GoogleTokenVerifier.verifyToken(idToken);
            if (payload != null) {
                Usuario usuario = repository.findByEmail(payload.getEmail());
                if (usuario == null) {
                    String email = payload.getEmail();
                    String nameAndUsername = payload.get("name").toString();
                    String senha = Cripter.criptografar(GeradorSenhaAleatoria.generate(16));
                    repository.save(new Usuario(nameAndUsername, nameAndUsername, email, senha));
                    usuario = repository.findByEmail(nameAndUsername);
                }
                String token = new TokenManager().generateToken(usuario);
                TokenDTO dto = new TokenDTO(token);
                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token inválido");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao verificar token: " + e.getMessage());
        }
    }

}
