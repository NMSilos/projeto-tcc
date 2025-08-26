package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.livros.BuscarLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.RespostaLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.RespostaLivroNoListDTO;
import com.github.nmsilos.backendtcc.exception.custom.LivroJaCadastradoException;
import com.github.nmsilos.backendtcc.exception.custom.LivroNaoEncontradoException;
import com.github.nmsilos.backendtcc.mapper.livros.BuscarLivroMapper;
import com.github.nmsilos.backendtcc.mapper.livros.RespostaLivroMapper;
import com.github.nmsilos.backendtcc.mapper.livros.RespostaLivroNoListMapper;
import com.github.nmsilos.backendtcc.model.Comentario;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    private final Path folderPath = Paths.get("bookcovers");

    @Transactional
    public Livro cadastrar(Livro livro) {
        return repository.save(livro);
    }

    @Transactional(readOnly = true)
    public Livro buscarInfo(Long idLivro) {
        Livro livroSalvo = repository.findById(idLivro).orElseThrow(
                () -> new LivroNaoEncontradoException("Erro ao buscar: Livro não encontrado")
        );
        return livroSalvo;
    }

    @Transactional(readOnly = true)
    public Livro buscarIsbn(String isbn) {
        Livro livroSalvo = repository.findByIsbn(isbn);
        if (livroSalvo == null) {
            throw new LivroNaoEncontradoException("Livro não encontrado.");
        }
        return livroSalvo;
    }

    @Transactional(readOnly = true)
    public List<BuscarLivroDTO> buscarPorTitulo(String query) {
        List<Livro> livros = repository.findByTituloContainingIgnoreCase(query);
        List<BuscarLivroDTO> buscados = livros.stream().map(BuscarLivroMapper::toDto).toList();
        return buscados;
    }

    @Transactional
    protected void atualizarAvaliacao(Long id) {
        Livro livro = buscarInfo(id);
        double somaNotas = livro.getComentarios().stream().mapToDouble(Comentario::getNota).sum();
        double avaliacao = somaNotas / livro.getComentarios().size();
        livro.setAvaliacao(avaliacao);
    }

    @Transactional(readOnly = true)
    public List<BuscarLivroDTO> buscarTodos() {
        List<Livro> livros = repository.findAll();
        return livros.stream().map(BuscarLivroMapper::toDto).toList();
    }

    @Transactional
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
