package com.github.nmsilos.backendtcc.dto.leituras;

import com.github.nmsilos.backendtcc.dto.comentarios.RespostaComentarioDTO;

import java.util.Date;

public class LeituraNoLivroDTO {

    private Long id;
    private Date data_inicio;
    private Date data_termino;
    private int pagina_atual;
    private boolean abandonado;
    private RespostaComentarioDTO comentario;

    public LeituraNoLivroDTO(
            Long id,
            Date data_inicio,
            Date data_termino,
            int pagina_atual,
            boolean abandonado,
            RespostaComentarioDTO comentario
    ) {
        this.id = id;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.pagina_atual = pagina_atual;
        this.abandonado = abandonado;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_termino() {
        return data_termino;
    }

    public void setData_termino(Date data_termino) {
        this.data_termino = data_termino;
    }

    public int getPagina_atual() {
        return pagina_atual;
    }

    public void setPagina_atual(int pagina_atual) {
        this.pagina_atual = pagina_atual;
    }

    public boolean isAbandonado() {
        return abandonado;
    }

    public void setAbandonado(boolean abandonado) {
        this.abandonado = abandonado;
    }

    public RespostaComentarioDTO getComentario() {
        return comentario;
    }

    public void setComentario(RespostaComentarioDTO comentario) {
        this.comentario = comentario;
    }
}
