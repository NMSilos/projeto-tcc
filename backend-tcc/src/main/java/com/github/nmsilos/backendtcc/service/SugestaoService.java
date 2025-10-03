package com.github.nmsilos.backendtcc.service;

import com.github.nmsilos.backendtcc.model.Sugestao;
import com.github.nmsilos.backendtcc.repository.SugestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SugestaoService {

    @Autowired
    private SugestaoRepository repository;

    @Transactional
    public Sugestao cadastrar(Sugestao sugestao) {
        return repository.save(sugestao);
    }

    @Transactional(readOnly = true)
    public List<Sugestao> buscarTodos() {
        return repository.findAll();
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
