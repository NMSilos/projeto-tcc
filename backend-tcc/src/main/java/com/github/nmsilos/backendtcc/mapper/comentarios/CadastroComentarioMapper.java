package com.github.nmsilos.backendtcc.mapper.comentarios;

import com.github.nmsilos.backendtcc.dto.comentarios.CadastroComentarioDTO;
import com.github.nmsilos.backendtcc.model.Comentario;

public class CadastroComentarioMapper {
    public static Comentario toModel(CadastroComentarioDTO dto) {
        return new Comentario(
                dto.getId(),
                dto.getTexto(),
                dto.getNota(),
                dto.getLivro(),
                dto.getLeitura()
        );
    }
}
