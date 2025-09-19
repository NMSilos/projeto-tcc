package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.model.Sugestao;
import com.github.nmsilos.backendtcc.repository.SugestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SugestaoService {

    @Autowired
    private SugestaoRepository repository;

    @Transactional
    public Sugestao cadastrar(Sugestao sugestao) {
        return repository.save(sugestao);
    }
}
