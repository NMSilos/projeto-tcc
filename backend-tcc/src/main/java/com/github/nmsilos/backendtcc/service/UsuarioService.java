package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.usuarios.*;
import com.github.nmsilos.backendtcc.exception.custom.ErroServidorException;
import com.github.nmsilos.backendtcc.exception.custom.UsuarioInvalidoException;
import com.github.nmsilos.backendtcc.exception.custom.TokenInvalidoException;
import com.github.nmsilos.backendtcc.mapper.usuarios.RespostaUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.AdminRepository;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.security.Cripter;
import com.github.nmsilos.backendtcc.security.TokenManager;
import com.github.nmsilos.backendtcc.utils.GeradorSenhaAleatoria;
import com.github.nmsilos.backendtcc.security.GoogleTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager manager;

    private final Path folderPath = Paths.get("uploads");

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
            if (authentication.getPrincipal() instanceof Usuario) {
                Admin usuario = (Usuario) authentication.getPrincipal();
                String token = new TokenManager().generateToken(usuario);
                TokenDTO dto = new TokenDTO(usuario.getId(), token);
                return dto;
            } else if (authentication.getPrincipal() instanceof Admin) {
                Admin usuario = (Admin) authentication.getPrincipal();
                String token = new TokenManager().generateToken(usuario);
                TokenDTO dto = new TokenDTO(usuario.getId(), token);
                return dto;
            }
            return null;
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
            throw new UsuarioInvalidoException("Erro ao efetuar login: Usuário ou senha incorretos");
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
                    String nome = payload.get("name").toString();
                    String username = "@user" + payload.get("sub").toString();
                    String senha = Cripter.criptografar(GeradorSenhaAleatoria.generate(16));
                    String imagem = payload.get("picture").toString();
                    repository.save(new Usuario(nome, username, email, senha, imagem, null));
                    usuario = repository.findByEmail(email);
                }
                String token = new TokenManager().generateToken(usuario);
                TokenDTO dto = new TokenDTO(usuario.getId(), token);
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
    public Usuario buscarPorId(Long idUsuario) {
        Usuario usuario = repository.findById(idUsuario).orElseThrow(
                        () -> new EntityNotFoundException("Usuário não encontrado")
        );
        return usuario;
    }

    public Usuario buscarPorUsername(String username) {
        Usuario usuario = repository.findByUsername(username);
        if (usuario == null) {
            throw new EntityNotFoundException(String.format("Usuário '%s' não encontrado", username));
        } else {
            return usuario;
        }
    }

    @Transactional
    public TokenDTO modificar(Usuario usuario, Usuario novoUsuario) {
        Usuario usuarioAtual = buscarPorId(usuario.getId());
        try {
            if (novoUsuario.getNome() != null) {
                usuarioAtual.setNome(novoUsuario.getNome());
            }
            if (novoUsuario.getUsername() != null) {
                usuarioAtual.setUsername(novoUsuario.getUsername());
            }

            if (novoUsuario.getPassword() != null && !novoUsuario.getPassword().isEmpty()) {
                usuarioAtual.setPassword(Cripter.criptografar(novoUsuario.getPassword()));
            } else {
                novoUsuario.setPassword(usuarioAtual.getPassword());
            }
            String token = new TokenManager().generateToken(usuarioAtual);
            return new TokenDTO(usuarioAtual.getId(), token);
        }
        catch (NullPointerException e) {
            //trocar por entitynotfound
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

    public Usuario buscarPorImagem(String imagem) {
        return repository.findByImagem(imagem);
    }

    public String salvarImagem(MultipartFile image) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String extensao = "";
        if (image.getOriginalFilename().contains(".")) {
            int aux = image.getOriginalFilename().lastIndexOf(".");
            extensao = image.getOriginalFilename().substring(aux);
        }

        String nomeImage = uuid.concat(extensao);

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Files.copy(image.getInputStream(), folderPath.resolve(nomeImage));
        return nomeImage;
    }

}
