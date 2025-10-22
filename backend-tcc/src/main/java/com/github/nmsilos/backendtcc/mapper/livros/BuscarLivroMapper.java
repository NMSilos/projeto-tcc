package com.github.nmsilos.backendtcc.mapper.livros;

import com.github.nmsilos.backendtcc.dto.livros.BuscarLivroDTO;
import com.github.nmsilos.backendtcc.model.Livro;

public class BuscarLivroMapper {
    public static BuscarLivroDTO toDto(Livro livro) {
        return new BuscarLivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getImagem(),
                livro.getAno_publicacao(),
                livro.getAvaliacao()
        );
    }
}
