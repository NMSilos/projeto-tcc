package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.exception.custom.LivroJaCadastradoException;
import com.github.nmsilos.backendtcc.exception.custom.LivroNaoEncontradoException;
import com.github.nmsilos.backendtcc.exception.custom.UsuarioInvalidoException;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager manager;

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
}
