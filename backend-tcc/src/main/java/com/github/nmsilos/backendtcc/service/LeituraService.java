package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.leituras.CadastroLeituraDTO;
import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.enums.StatusLeitura;
import com.github.nmsilos.backendtcc.exception.custom.UsuarioInvalidoException;
import com.github.nmsilos.backendtcc.mapper.leituras.CadastroLeituraMapper;
import com.github.nmsilos.backendtcc.mapper.leituras.RespostaLeituraMapper;
import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.model.Livro;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.LeituraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class LeituraService {

    @Autowired
    private LeituraRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @Transactional
    public RespostaLeituraDTO criarLeitura(Usuario usuarioLogado, CadastroLeituraDTO leitura) {
        Usuario usuario = usuarioService.buscarPorId(leitura.getUsuario().getId());
        Livro livro = livroService.buscarInfo(leitura.getLivro().getId());

        if (usuario != null && usuario.equals(usuarioLogado)) {
            Leitura novaLeitura = CadastroLeituraMapper.toModel(leitura);

            novaLeitura.setUsuario(usuarioLogado);
            novaLeitura.setLivro(livro);
            if(novaLeitura.getStatus().equals(StatusLeitura.LIDO)) {
                novaLeitura.setPagina_atual(novaLeitura.getLivro().getPaginas());
                System.out.println(novaLeitura.getPagina_atual());
            }

            usuario.getLeituras().add(novaLeitura);

            repository.save(novaLeitura);
            repository.flush();
            
            return RespostaLeituraMapper.toDto(novaLeitura);
        } else {
            throw new UsuarioInvalidoException("Erro ao salvar: Usuário não autorizado");
        }
    }

    @Transactional(readOnly = true)
    public Leitura buscarInfo(Long id) {
        Leitura leitura = repository.getReferenceById(id);
        if (leitura == null) {
            throw new EntityNotFoundException("Leitura não encontrada");
        } else {
            return leitura;
        }
    }

    @Transactional
    public void excluir(Usuario usuarioLogado, Long userId, Livro livro) {
        Usuario usuario = usuarioService.buscarPorId(userId);
        if (usuario != null && usuario.equals(usuarioLogado)) {
            usuario.getLeituras().forEach(leitura -> {
                if (leitura.getLivro().getId().equals(livro.getId())) {
                    repository.deleteById(leitura.getId());
                }
            });
        }
    }

    @Transactional(readOnly = true)
    public List<Leitura> buscarPorStatus(StatusLeitura status, String username) {
        Usuario usuario = usuarioService.buscarPorUsername(username);
        List<Leitura> leituras = usuario.getLeituras()
                .stream()
                .filter(leitura -> leitura.getStatus().equals(status))
                .toList();
        return leituras;
    }
}
