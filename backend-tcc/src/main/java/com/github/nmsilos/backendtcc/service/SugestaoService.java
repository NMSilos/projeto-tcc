package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.dto.sugestoes.RespostaSugestaoDTO;
import com.github.nmsilos.backendtcc.enums.StatusSugestao;
import com.github.nmsilos.backendtcc.mapper.sugestoes.RespostaSugestaoMapper;
import com.github.nmsilos.backendtcc.model.Sugestao;
import com.github.nmsilos.backendtcc.repository.SugestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SugestaoService {

    @Autowired
    private SugestaoRepository repository;

    @Transactional
    public Sugestao cadastrar(Sugestao sugestao) {
        sugestao.setStatus(StatusSugestao.PENDENTE);
        return repository.save(sugestao);
    }

    @Transactional(readOnly = true)
    public List<RespostaSugestaoDTO> buscarTodos() {
        List<Sugestao> sugestoes = repository.findAll();
        return sugestoes.stream().map(RespostaSugestaoMapper::toDto).toList();
    }

    @Transactional
    public Void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            return null;
        }
        return null;
    }
}
