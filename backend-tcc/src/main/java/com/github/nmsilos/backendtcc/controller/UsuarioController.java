package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.usuarios.*;
import com.github.nmsilos.backendtcc.exception.custom.ErroServidorException;
import com.github.nmsilos.backendtcc.mapper.usuarios.CadastroUsuarioMapper;
import com.github.nmsilos.backendtcc.mapper.usuarios.RespostaUsuarioMapper;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.security.Cripter;
import com.github.nmsilos.backendtcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    private final Path folderPath = Paths.get("uploads");

    @PostMapping("/cadastrar")
    public ResponseEntity<RespostaUsuarioDTO> cadastrarUsuario(
            @RequestParam ("nome") String nome,
            @RequestParam ("username") String username,
            @RequestParam ("password") String password,
            @RequestParam ("email") String email,
            @RequestParam (value = "imagem", required = false) MultipartFile image) {

        try {
            String nomeImage = null;
            String typeImage = null;
            if (image != null) {
                nomeImage = service.salvarImagem(image);
                typeImage = image.getContentType();
            }
            Usuario usuario = new Usuario(nome, username, email, Cripter.criptografar(password), nomeImage, typeImage);
            RespostaUsuarioDTO resposta = service.cadastrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        }
        catch (Exception e) {
            throw new ErroServidorException("Erro ao salvar usuário. Tente mais tarde");
        }
    }

    @GetMapping("/userImage/{imagem}")
    public ResponseEntity<byte[]> buscarImagem(@PathVariable String imagem) throws IOException {
        Usuario user = service.buscarPorImagem(imagem);
        Path imagemPath = Paths.get(folderPath.toString(), imagem);
        byte[] imagemBytes = Files.readAllBytes(imagemPath);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(user.getContentType())).body(imagemBytes);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dados) {
        TokenDTO token = service.login(dados);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest request) {
        TokenDTO token = service.loginComGoogle(request);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<RespostaUsuarioDTO> buscarPorId(@PathVariable Long id) {
        RespostaUsuarioDTO dto = RespostaUsuarioMapper.toDto(service.buscarPorId(id));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/buscar/username/{username}")
    public ResponseEntity<RespostaUsuarioDTO> buscarPorUsername(@PathVariable String username) {
        RespostaUsuarioDTO dto = RespostaUsuarioMapper.toDto(service.buscarPorUsername(username));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/modificar")
    public ResponseEntity<TokenDTO> modificarUsuario(@AuthenticationPrincipal Usuario usuario, @RequestBody ModificarUsuarioDTO novoUsuario) {
        TokenDTO dto = service.modificar(usuario, CadastroUsuarioMapper.toModel(novoUsuario));
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/encerrar-conta")
    public ResponseEntity encerrarConta(@AuthenticationPrincipal Usuario usuario, @RequestBody Usuario contaEncerrada) {
        service.deletar(usuario, contaEncerrada);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
