package com.github.nmsilos.backendtcc.dto.comentarios;

import com.github.nmsilos.backendtcc.model.Leitura;
import com.github.nmsilos.backendtcc.model.Livro;

public class CadastroComentarioDTO {

    private Long id;
    private String texto;
    private int nota;
    private Livro livro;
    private Leitura leitura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitura getLeitura() {
        return leitura;
    }

    public void setLeitura(Leitura leitura) {
        this.leitura = leitura;
    }

}
