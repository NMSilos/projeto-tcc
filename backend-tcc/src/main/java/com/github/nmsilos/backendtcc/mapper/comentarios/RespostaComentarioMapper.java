package com.github.nmsilos.backendtcc.mapper.comentarios;

import com.github.nmsilos.backendtcc.dto.comentarios.RespostaComentarioDTO;
import com.github.nmsilos.backendtcc.model.Comentario;

public class RespostaComentarioMapper {
    public static RespostaComentarioDTO toDto(Comentario comentario) {
        return new RespostaComentarioDTO(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getNota()
        );
    }
}
