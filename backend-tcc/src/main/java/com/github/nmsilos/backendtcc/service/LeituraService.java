package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.leituras.CadastroLeituraDTO;
import com.github.nmsilos.backendtcc.dto.leituras.EditarLeituraDTO;
import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.enums.StatusLeitura;
import com.github.nmsilos.backendtcc.exception.custom.NumeroPaginasInvalidoException;
import com.github.nmsilos.backendtcc.exception.custom.UsuarioInvalidoException;
import com.github.nmsilos.backendtcc.mapper.leituras.CadastroLeituraMapper;
import com.github.nmsilos.backendtcc.mapper.leituras.RespostaLeituraMapper;
import com.github.nmsilos.backendtcc.model.*;
import com.github.nmsilos.backendtcc.repository.LeituraRepository;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.utils.LeituraUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LeituraService {

    @Autowired
    private LeituraRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

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

            if(novaLeitura.getStatus().equals(StatusLeitura.LIDO) || novaLeitura.getStatus().equals(StatusLeitura.LENDO)) {
                boolean data_atualizada = LeituraUtils.verificarDataStreak(usuario.getUltima_leitura());

                if (!data_atualizada) {
                    usuario.setUltima_leitura(new Date());
                    usuario.setStreaks(usuario.getStreaks() + 1);
                }
            }

            usuario.getLeituras().add(novaLeitura);

            repository.save(novaLeitura);
            System.out.println(usuario.getUltima_leitura());
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

    @Transactional
    public void editarLeitura(EditarLeituraDTO leitura) {
        Leitura novaLeitura = repository.getReferenceById(leitura.getId());
        novaLeitura.setStatus(leitura.getStatus());
        if (novaLeitura.getStatus().equals(StatusLeitura.LIDO)) {
            if (novaLeitura.getData_termino() == null) {
                novaLeitura.setData_termino(new Date());
            } else {
                novaLeitura.setData_termino(leitura.getData_termino());
            }
            novaLeitura.setPagina_atual(novaLeitura.getLivro().getPaginas());
            novaLeitura.setData_inicio(leitura.getData_inicio());
        }
        else if (leitura.getStatus().equals(StatusLeitura.LENDO)) {
            if(leitura.getData_inicio() == null) {
                novaLeitura.setData_inicio(new Date());
            } else {
                novaLeitura.setData_inicio(leitura.getData_inicio());
            }
        }
        else if (novaLeitura.getStatus().equals(StatusLeitura.PRETENDO_LER)) {
            novaLeitura.setData_inicio(null);
            novaLeitura.setData_termino(null);
        }
        else if (novaLeitura.getStatus().equals(StatusLeitura.ABANDONADO)) {
            novaLeitura.setData_termino(leitura.getData_termino());
        }

        if (!novaLeitura.getStatus().equals(StatusLeitura.LIDO)) {
            if(verificarNumeroPaginas(leitura.getPagina_atual(), novaLeitura.getLivro().getPaginas())) {
                novaLeitura.setPagina_atual(leitura.getPagina_atual());
            }
        }

        if(novaLeitura.getComentario() == null) {
            Comentario comentario = new Comentario();
            comentario.setTexto(leitura.getComentario().getTexto());
            comentario.setNota(leitura.getComentario().getNota());
            comentario.setLivro(novaLeitura.getLivro());
            comentario.setLeitura(novaLeitura);

            comentarioService.comentar(comentario);
        } else {
            novaLeitura.getComentario().setTexto(leitura.getComentario().getTexto());
            novaLeitura.getComentario().setNota(leitura.getComentario().getNota());

            comentarioService.comentar(novaLeitura.getComentario());
        }

        if (!novaLeitura.getStatus().equals(StatusLeitura.PRETENDO_LER)) {
            Usuario usuario = usuarioRepository.getReferenceById(novaLeitura.getUsuario().getId());
            if (!LeituraUtils.verificarDataStreak(usuario.getUltima_leitura())) {
                usuario.setUltima_leitura(new Date());
                usuario.setStreaks(usuario.getStreaks() + 1);
            }
        }

        repository.save(novaLeitura);
    }


    public boolean verificarNumeroPaginas(int paginaAtual, int numPaginas) {
        if(numPaginas < paginaAtual) {
            throw new NumeroPaginasInvalidoException("Página atual inválida");
        } else {
            return true;
        }
    }

}
