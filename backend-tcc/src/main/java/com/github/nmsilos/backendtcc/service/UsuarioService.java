package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.ErroDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.GoogleLoginRequest;
import com.github.nmsilos.backendtcc.dto.usuarios.LoginDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.RespostaUsuarioDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.TokenDTO;
import com.github.nmsilos.backendtcc.mapper.usuarios.RespostaUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.security.Cripter;
import com.github.nmsilos.backendtcc.security.TokenManager;
import com.github.nmsilos.backendtcc.utils.GeradorSenhaAleatoria;
import com.github.nmsilos.backendtcc.utils.GoogleTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Transactional
    public RespostaUsuarioDTO cadastrar(Usuario usuario) {
        repository.save(usuario);
        return RespostaUsuarioMapper.toDto(usuario);
    }

    @Transactional
    public TokenDTO login(LoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getUsername(), dados.getPassword());
        try{
            var authentication = manager.authenticate(authenticationToken);
            Usuario usuario = (Usuario) authentication.getPrincipal();
            String token = new TokenManager().generateToken(usuario);
            TokenDTO dto = new TokenDTO(token);
            return dto;
        } catch (AuthenticationException e) {
            ErroDTO erro = new ErroDTO("Usuário/Senha incorreta");
            //return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    //.body(erro);
            System.out.println(erro);
        }
        return null;
    }

    public TokenDTO loginComGoogle(GoogleLoginRequest request) {
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
                return dto;
            } else {
                //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token inválido");
            }
        } catch (Exception e) {
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao verificar token: " + e.getMessage());
        }
        return null;
    }
}
