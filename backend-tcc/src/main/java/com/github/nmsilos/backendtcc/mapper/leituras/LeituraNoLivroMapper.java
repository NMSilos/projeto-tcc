package com.github.nmsilos.backendtcc.mapper.leituras;

import com.github.nmsilos.backendtcc.dto.leituras.LeituraNoLivroDTO;
import com.github.nmsilos.backendtcc.mapper.comentarios.RespostaComentarioMapper;
import com.github.nmsilos.backendtcc.model.Leitura;

public class LeituraNoLivroMapper {
    public static LeituraNoLivroDTO toDto(Leitura leitura) {
        return new LeituraNoLivroDTO(
                leitura.getId(),
                leitura.getData_inicio(),
                leitura.getData_termino(),
                leitura.getPagina_atual(),
                leitura.isAbandonado(),
                RespostaComentarioMapper.toDto(leitura.getComentario())
        );
    }
}
