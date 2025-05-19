package com.github.nmsilos.backendtcc.mapper.leituras;

import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.mapper.livros.RespostaLivroNoListMapper;
import com.github.nmsilos.backendtcc.model.Leitura;

public class RespostaLeituraMapper {
    public static RespostaLeituraDTO toDto(Leitura leitura) {
        return new RespostaLeituraDTO(
                leitura.getId(),
                leitura.getData_inicio(),
                leitura.getData_termino(),
                leitura.getPagina_atual(),
                leitura.isAbandonado(),
                RespostaLivroNoListMapper.toNoListDTO(leitura.getLivro())
        );
    }
}
