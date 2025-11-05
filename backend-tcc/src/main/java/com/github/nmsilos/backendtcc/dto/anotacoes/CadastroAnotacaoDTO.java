package com.github.nmsilos.backendtcc.dto.anotacoes;

import com.github.nmsilos.backendtcc.model.Leitura;
import jakarta.persistence.*;

import java.util.Date;

public class CadastroAnotacaoDTO {

    private String titulo;

    @Column(length = 3000)
    private String descricao;

    private int capitulo;
    private int pagina;
    private Date data;
    private Leitura leitura;

    public CadastroAnotacaoDTO(String titulo, String descricao, int capitulo, int pagina, Date data, Leitura leitura) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.capitulo = capitulo;
        this.pagina = pagina;
        this.data = data;
        this.leitura = leitura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Leitura getLeitura() {
        return leitura;
    }

    public void setLeitura(Leitura leitura) {
        this.leitura = leitura;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
