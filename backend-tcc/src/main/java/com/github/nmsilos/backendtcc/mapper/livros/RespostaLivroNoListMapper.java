package com.github.nmsilos.backendtcc.mapper.livros;

import com.github.nmsilos.backendtcc.dto.livros.RespostaLivroNoListDTO;
import com.github.nmsilos.backendtcc.model.Livro;

public class RespostaLivroNoListMapper {
    public static RespostaLivroNoListDTO toNoListDTO(Livro livro) {
        return new RespostaLivroNoListDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getEditora(),
                livro.getAno_publicacao(),
                livro.getPaginas(),
                livro.getDescricao(),
                livro.getAvaliacao(),
                livro.getImagem()
        );
    }
}
