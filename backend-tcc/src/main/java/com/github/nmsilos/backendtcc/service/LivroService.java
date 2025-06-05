package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.livros.BuscarLivroDTO;
import com.github.nmsilos.backendtcc.exception.custom.LivroJaCadastradoException;
import com.github.nmsilos.backendtcc.exception.custom.LivroNaoEncontradoException;
import com.github.nmsilos.backendtcc.mapper.livros.BuscarLivroMapper;
import com.github.nmsilos.backendtcc.model.Comentario;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Transactional
    public Livro cadastrar(Livro livro) {
        Livro livroSalvo = repository.findByIsbn(livro.getIsbn());
        if (livroSalvo != null) {
            throw new LivroJaCadastradoException("Erro ao salvar: Livro já cadastrado");
        } else {
            return repository.save(livro);
        }
    }

    @Transactional(readOnly = true)
    public Livro buscarInfo(Long idLivro) {
        Livro livroSalvo = repository.findById(idLivro).orElseThrow(
                () -> new LivroNaoEncontradoException("Erro ao buscar: Livro não encontrado")
        );
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
}
