package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.dto.livros.BuscarLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.CadastroLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.RespostaLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.RespostaLivroNoListDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.RespostaUsuarioDTO;
import com.github.nmsilos.backendtcc.exception.custom.ErroServidorException;
import com.github.nmsilos.backendtcc.mapper.livros.CadastroLivroMapper;
import com.github.nmsilos.backendtcc.mapper.livros.RespostaLivroMapper;
import com.github.nmsilos.backendtcc.mapper.livros.RespostaLivroNoListMapper;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.security.Cripter;
import com.github.nmsilos.backendtcc.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    private final Path folderPath = Paths.get("bookcovers");

    @PostMapping("/cadastrar")
    public ResponseEntity<Livro> cadastrar(
            @RequestParam ("titulo") String titulo,
            @RequestParam ("autor") String autor,
            @RequestParam ("editora") String editora,
            @RequestParam ("ano_publicacao") int ano_publicacao,
            @RequestParam ("paginas") int paginas,
            @RequestParam ("isbn") String isbn,
            @RequestParam ("descricao") String descricao,
            @RequestParam (value = "imagem", required = false) MultipartFile image)
    {
        try {
            String nomeImage = null;
            String typeImage = null;
            if (image != null) {
                nomeImage = service.salvarImagem(image);
                typeImage = image.getContentType();
            }
            Year ano = Year.of(ano_publicacao);
            Livro livro = new Livro(titulo, autor, isbn, editora, ano, paginas, descricao, 0, nomeImage, typeImage);
            //RespostaLivroDTO resposta = service.cadastrar(livro);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(livro));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ErroServidorException("Erro ao salvar usuário. Tente mais tarde");
        }
    }

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<RespostaLivroNoListDTO> buscarPorId(@AuthenticationPrincipal Admin usuario, @PathVariable Long id) {
        RespostaLivroNoListDTO livro = RespostaLivroNoListMapper.toNoListDTO(service.buscarInfo(id));
        return ResponseEntity.ok().body(livro);
    }

    @GetMapping("/buscar-isbn/{isbn}")
    public ResponseEntity<RespostaLivroDTO> buscarPorIsbn(@AuthenticationPrincipal Admin usuario, @PathVariable String isbn) {
        RespostaLivroDTO livro = RespostaLivroMapper.toDTO(service.buscarIsbn(isbn));
        return ResponseEntity.ok().body(livro);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<BuscarLivroDTO>> buscarPorTitulo(@AuthenticationPrincipal Admin usuario, @RequestParam("query") String query) {
        List<BuscarLivroDTO> livros = service.buscarPorTitulo(query);
        return ResponseEntity.ok().body(livros);
    }

    @GetMapping("/buscar/all")
    public ResponseEntity<List<BuscarLivroDTO>> buscarTodos() {
        List<BuscarLivroDTO> livros = service.buscarTodos();
        return ResponseEntity.ok().body(livros);
    }

    @GetMapping("/livroImage/{imagem}")
    public ResponseEntity<byte[]> buscarImagem(@PathVariable String imagem) throws IOException {
        Livro livro = service.buscarPorImagem(imagem);
        Path imagemPath = Paths.get(folderPath.toString(), imagem);
        byte[] imagemBytes = Files.readAllBytes(imagemPath);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(livro.getContentType())).body(imagemBytes);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
