package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.anotacoes.CadastroAnotacaoDTO;
import com.github.nmsilos.backendtcc.dto.anotacoes.RespostaAnotacaoDTO;
import com.github.nmsilos.backendtcc.mapper.anotacoes.CadastroAnotacaoMapper;
import com.github.nmsilos.backendtcc.mapper.anotacoes.RespostaAnotacaoMapper;
import com.github.nmsilos.backendtcc.model.Anotacao;
import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.repository.AnotacaoRepository;
import com.github.nmsilos.backendtcc.repository.LeituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnotacaoService {

    @Autowired
    private AnotacaoRepository repository;

    @Autowired
    private LeituraRepository leituraRepository;

    public RespostaAnotacaoDTO cadastrar(CadastroAnotacaoDTO dto) {
        Anotacao anotacao = CadastroAnotacaoMapper.toModel(dto);
        Leitura leitura = leituraRepository.getReferenceById(anotacao.getLeitura().getId());

        anotacao.setLeitura(leitura);
        leitura.getAnotacoes().add(anotacao);
        repository.save(anotacao);

        return RespostaAnotacaoMapper.toDto(anotacao);
    }
}
