package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.anotacoes.CadastroAnotacaoDTO;
import com.github.nmsilos.backendtcc.dto.anotacoes.RespostaAnotacaoDTO;
import com.github.nmsilos.backendtcc.mapper.anotacoes.CadastroAnotacaoMapper;
import com.github.nmsilos.backendtcc.mapper.anotacoes.RespostaAnotacaoMapper;
import com.github.nmsilos.backendtcc.model.Anotacao;
import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.repository.AnotacaoRepository;
import com.github.nmsilos.backendtcc.repository.LeituraRepository;
import com.github.nmsilos.backendtcc.repository.UsuarioRepository;
import com.github.nmsilos.backendtcc.utils.LeituraUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AnotacaoService {

    @Autowired
    private AnotacaoRepository repository;

    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public RespostaAnotacaoDTO cadastrar(CadastroAnotacaoDTO dto) {
        Anotacao anotacao = CadastroAnotacaoMapper.toModel(dto);
        Leitura leitura = leituraRepository.getReferenceById(anotacao.getLeitura().getId());

        anotacao.setLeitura(leitura);
        leitura.getAnotacoes().add(anotacao);

        Usuario usuario = usuarioRepository.getReferenceById(leitura.getUsuario().getId());
        if (!LeituraUtils.verificarDataStreak(usuario.getUltima_leitura())) {
            usuario.setUltima_leitura(new Date());
            usuario.setStreaks(usuario.getStreaks() + 1);
        }

        repository.save(anotacao);

        return RespostaAnotacaoMapper.toDto(anotacao);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public RespostaAnotacaoDTO editar(Long id, CadastroAnotacaoDTO dto) {
        Anotacao anotacao = repository.getReferenceById(id);
        anotacao.setTitulo(dto.getTitulo());
        anotacao.setDescricao(dto.getDescricao());
        anotacao.setCapitulo(dto.getCapitulo());
        anotacao.setPagina(dto.getPagina());
        anotacao.setData(dto.getData());
        return RespostaAnotacaoMapper.toDto(anotacao);
    }
}
