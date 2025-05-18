package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.usuarios.GoogleLoginRequest;
import com.github.nmsilos.backendtcc.dto.usuarios.LoginDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.RespostaUsuarioDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.TokenDTO;
import com.github.nmsilos.backendtcc.exception.custom.ErroServidorException;
import com.github.nmsilos.backendtcc.exception.custom.LoginInvalidoException;
import com.github.nmsilos.backendtcc.exception.custom.TokenInvalidoException;
import com.github.nmsilos.backendtcc.mapper.usuarios.RespostaUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.security.Cripter;
import com.github.nmsilos.backendtcc.security.TokenManager;
import com.github.nmsilos.backendtcc.utils.GeradorSenhaAleatoria;
import com.github.nmsilos.backendtcc.utils.GoogleTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
        }
        catch (AuthenticationException e) {
            throw new LoginInvalidoException("Erro ao efetuar login: Usuário ou senha incorretos");
        }
    }

    @Transactional
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
                    usuario = repository.findByEmail(email);
                }
                String token = new TokenManager().generateToken(usuario);
                TokenDTO dto = new TokenDTO(token);
                return dto;
            } else {
                throw new TokenInvalidoException("Erro ao efetuar login: Token inválido");
            }
        }
        catch (Exception e) {
            throw new ErroServidorException("Erro ao verificar token: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarInfo(Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario).orElseThrow(
                        () -> new EntityNotFoundException("Usuário não encontrado")
        );
        return usuario;
    }

    @Transactional
    public Usuario modificar(Usuario usuario, Usuario novoUsuario) {
        Usuario usuarioAtual = buscarInfo(usuario.getId());
        try {
            if (usuarioAtual != null && usuarioAtual.equals(novoUsuario)) {
                usuarioAtual.setNome(novoUsuario.getNome());
                usuarioAtual.setUsername(novoUsuario.getUsername());
                usuarioAtual.setPassword(Cripter.criptografar(novoUsuario.getPassword()));
            }
            return usuarioAtual;
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Erro ao atualizar usuário");
        }
    }

    @Transactional
    public void deletar(Usuario usuario, Usuario contaEncerrada) {
        try {
            if (usuario.equals(contaEncerrada)) {
                repository.deleteById(usuario.getId());
            } else {
                throw new SecurityException("Usuário não autorizado a deletar esta conta.");
            }
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException("Erro ao encerrar conta");
        }
    }
}
