package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.comentarios.CadastroComentarioDTO;
import com.github.nmsilos.backendtcc.dto.comentarios.RespostaComentarioDTO;
import com.github.nmsilos.backendtcc.mapper.comentarios.CadastroComentarioMapper;
import com.github.nmsilos.backendtcc.mapper.comentarios.RespostaComentarioMapper;
import com.github.nmsilos.backendtcc.model.Comentario;
import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @Autowired
    private LivroService livroService;

    @Transactional
    public RespostaComentarioDTO comentar(Comentario comentario) {

        Livro livro = livroService.buscarInfo(comentario.getLivro().getId());
        Leitura leitura = comentario.getLeitura();
        leitura.setComentario(comentario);

        livro.getComentarios().add(comentario);
        livroService.atualizarAvaliacao(livro.getId());

        comentario.setLivro(livro);
        comentario.setLeitura(leitura);

        repository.save(comentario);

        return RespostaComentarioMapper.toDto(comentario);
    }

}
