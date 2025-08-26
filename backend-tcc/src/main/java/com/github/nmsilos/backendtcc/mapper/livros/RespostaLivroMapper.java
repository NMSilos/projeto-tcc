package com.github.nmsilos.backendtcc.mapper.livros;

import com.github.nmsilos.backendtcc.dto.leituras.LeituraNoLivroDTO;
import com.github.nmsilos.backendtcc.dto.livros.RespostaLivroDTO;
import com.github.nmsilos.backendtcc.mapper.leituras.LeituraNoLivroMapper;
import com.github.nmsilos.backendtcc.model.Livro;

import java.util.List;
import java.util.stream.Collectors;

public class RespostaLivroMapper {
    public static RespostaLivroDTO toDTO(Livro livro) {

        List<LeituraNoLivroDTO> leituras = livro.getLeituras().stream()
                .map(LeituraNoLivroMapper::toDto).toList();

        return new RespostaLivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getEditora(),
                livro.getAno_publicacao(),
                livro.getPaginas(),
                livro.getDescricao(),
                livro.getAvaliacao(),
                livro.getImagem(), 
                leituras
        );
    }
}
