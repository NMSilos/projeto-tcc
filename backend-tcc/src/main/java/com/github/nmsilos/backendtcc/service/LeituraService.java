package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.leituras.CadastroLeituraDTO;
import com.github.nmsilos.backendtcc.exception.custom.UsuarioInvalidoException;
import com.github.nmsilos.backendtcc.mapper.leituras.CadastroLeituraMapper;
import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.LeituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeituraService {

    @Autowired
    private LeituraRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    public Leitura criarLeitura(Usuario usuarioLogado, CadastroLeituraDTO leitura) {
        Usuario usuario = usuarioService.buscarInfo(leitura.getUsuario().getId());
        Livro livro = livroService.buscarInfo(leitura.getLivro().getId());

        if (usuario != null && usuario.equals(usuarioLogado)) {
            Leitura novaLeitura = CadastroLeituraMapper.toModel(leitura);
            novaLeitura.setUsuario(usuarioLogado);
            novaLeitura.setLivro(livro);
            usuario.getLeituras().add(novaLeitura);
            return repository.save(novaLeitura);
        } else {
            throw new UsuarioInvalidoException("Erro ao salvar: Usuário não autorizado");
        }

    }
}
