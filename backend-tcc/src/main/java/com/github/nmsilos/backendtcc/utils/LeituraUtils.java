package com.github.nmsilos.backendtcc.utils;

import com.github.nmsilos.backendtcc.model.Usuario;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class LeituraUtils {

    public static boolean verificarDataAtual(Date dataUsuario) {
        if (dataUsuario == null) { return false; }

        LocalDate dataUltimaLeitura = dataUsuario.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate dataAtual = LocalDate.now();
        return dataUltimaLeitura.isEqual(dataAtual);
    }
}


