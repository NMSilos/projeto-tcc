package com.github.nmsilos.backendtcc.mapper.usuarios;

import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.RespostaUsuarioDTO;
import com.github.nmsilos.backendtcc.mapper.leituras.RespostaLeituraMapper;
import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class RespostaUsuarioMapper {
    public static RespostaUsuarioDTO toDto(Usuario usuario) {
        List<RespostaLeituraDTO> leituras = usuario.getLeituras().stream()
                .map(RespostaLeituraMapper::toDto)
                .collect(Collectors.toList());
        return new RespostaUsuarioDTO(
                usuario.getNome(),
                usuario.getUsername(),
                usuario.getEmail(),
                leituras
        );
    }
}
