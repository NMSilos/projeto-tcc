package com.github.nmsilos.backendtcc.mapper.leituras;

import com.github.nmsilos.backendtcc.dto.comentarios.RespostaComentarioDTO;
import com.github.nmsilos.backendtcc.dto.leituras.LeituraNoLivroDTO;
import com.github.nmsilos.backendtcc.mapper.comentarios.RespostaComentarioMapper;
import com.github.nmsilos.backendtcc.mapper.usuarios.UsuarioPrincipalMapper;
import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.model.Usuario;

public class LeituraNoLivroMapper {
    public static LeituraNoLivroDTO toDto(Leitura leitura) {

        RespostaComentarioDTO comentario;
        if (leitura.getComentario() == null) {
            comentario = null;
        } else {
            comentario = RespostaComentarioMapper.toDto(leitura.getComentario());
        }

        return new LeituraNoLivroDTO(
                leitura.getId(),
                leitura.getData_inicio(),
                leitura.getData_termino(),
                leitura.getPagina_atual(),
                leitura.getStatus(),
                UsuarioPrincipalMapper.toDto((Usuario) leitura.getUsuario()),
                comentario
        );
    }
}
