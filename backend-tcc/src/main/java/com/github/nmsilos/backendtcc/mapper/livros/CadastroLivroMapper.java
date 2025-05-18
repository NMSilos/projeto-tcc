package com.github.nmsilos.backendtcc.mapper.livros;

import com.github.nmsilos.backendtcc.dto.livros.CadastroLivroDTO;
import com.github.nmsilos.backendtcc.model.Livro;

public class CadastroLivroMapper {

    public static Livro toModel(CadastroLivroDTO livro) {
        return new Livro(
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getEditora(),
                livro.getAno_publicacao(),
                livro.getPaginas(),
                livro.getDescricao()
        );
    }

}
