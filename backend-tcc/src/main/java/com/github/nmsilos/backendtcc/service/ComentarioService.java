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

    @Autowired
    private LeituraService leituraService;

    @Transactional
    public RespostaComentarioDTO comentar(CadastroComentarioDTO comentario) {
        Comentario com = CadastroComentarioMapper.toModel(comentario);

        Livro livro = livroService.buscarInfo(comentario.getLivro().getId());
        Leitura leitura = leituraService.buscarInfo(comentario.getLeitura().getId());

        leitura.setComentario(com);
        livro.getComentarios().add(com);
        com.setLivro(livro);
        com.setLeitura(leitura);

        repository.save(com);

        return RespostaComentarioMapper.toDto(com);
    }
}
