package com.github.nmsilos.backendtcc.mapper.anotacoes;

import com.github.nmsilos.backendtcc.dto.anotacoes.CadastroAnotacaoDTO;
import com.github.nmsilos.backendtcc.model.Anotacao;

import java.util.Date;

public class CadastroAnotacaoMapper {

    public static Anotacao toModel(CadastroAnotacaoDTO dto) {
        return new Anotacao(
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getCapitulo(),
                dto.getPagina(),
                new Date(),
                dto.getLeitura()
        );
    }

}
