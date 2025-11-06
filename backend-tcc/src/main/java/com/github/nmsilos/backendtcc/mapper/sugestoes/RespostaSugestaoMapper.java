package com.github.nmsilos.backendtcc.mapper.sugestoes;

import com.github.nmsilos.backendtcc.dto.sugestoes.RespostaSugestaoDTO;
import com.github.nmsilos.backendtcc.model.Sugestao;

public class RespostaSugestaoMapper {
    public static RespostaSugestaoDTO toDto(Sugestao sugestao) {
        return new RespostaSugestaoDTO(
                sugestao.getId(),
                sugestao.getTitulo(),
                sugestao.getAutor(),
                sugestao.getEditora(),
                sugestao.getDescricao(),
                sugestao.getStatus()
        );
    }
}
