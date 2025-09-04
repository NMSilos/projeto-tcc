package com.github.nmsilos.backendtcc.mapper.leituras;

import com.github.nmsilos.backendtcc.dto.leituras.CadastroLeituraDTO;
import com.github.nmsilos.backendtcc.model.Leitura;

public class CadastroLeituraMapper {
    public static Leitura toModel(CadastroLeituraDTO leitura) {
        return new Leitura(
                leitura.getData_inicio(),
                leitura.getData_termino(),
                leitura.getPagina_atual(),
                leitura.getStatus()
        );
    }
}
