package com.github.nmsilos.backendtcc.dto.leituras;

import com.github.nmsilos.backendtcc.dto.comentarios.RespostaComentarioDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.UsuarioPrincipalDTO;
import com.github.nmsilos.backendtcc.enums.StatusLeitura;

import java.util.Date;

public class LeituraNoLivroDTO {

    private Long id;
    private Date data_inicio;
    private Date data_termino;
    private int pagina_atual;
    private StatusLeitura status;
    private UsuarioPrincipalDTO usuario;
    private RespostaComentarioDTO comentario;

    public LeituraNoLivroDTO(
            Long id,
            Date data_inicio,
            Date data_termino,
            int pagina_atual,
            StatusLeitura status,
            UsuarioPrincipalDTO usuario,
            RespostaComentarioDTO comentario
    ) {
        this.id = id;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.pagina_atual = pagina_atual;
        this.status = status;
        this.usuario = usuario;
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

    public StatusLeitura getStatus() {
        return status;
    }

    public void setStatus(StatusLeitura status) {
        this.status = status;
    }

    public UsuarioPrincipalDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPrincipalDTO usuario) {
        this.usuario = usuario;
    }

    public RespostaComentarioDTO getComentario() {
        return comentario;
    }

    public void setComentario(RespostaComentarioDTO comentario) {
        this.comentario = comentario;
    }
}
