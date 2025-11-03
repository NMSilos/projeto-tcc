package com.github.nmsilos.backendtcc.mapper.anotacoes;

import com.github.nmsilos.backendtcc.dto.anotacoes.RespostaAnotacaoDTO;
import com.github.nmsilos.backendtcc.model.Anotacao;

public class RespostaAnotacaoMapper {

    public static RespostaAnotacaoDTO toDto(Anotacao anotacao) {
        return new RespostaAnotacaoDTO(
                anotacao.getId(),
                anotacao.getDescricao(),
                anotacao.getCapitulo(),
                anotacao.getPagina(),
                anotacao.getData()
        );
    }
}
