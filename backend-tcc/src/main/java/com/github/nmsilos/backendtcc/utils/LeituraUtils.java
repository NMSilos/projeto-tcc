package com.github.nmsilos.backendtcc.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LeituraUtils {

    public static boolean verificarDataStreak(Date dataUsuario) {
        if (dataUsuario == null) { return false; }

        LocalDate dataUltimaLeitura = dataUsuario.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate dataAtual = LocalDate.now();
        return dataUltimaLeitura.isEqual(dataAtual);
    }

    public static boolean verificarMaisDeUmDia(Date dataUsuario) {
        if (dataUsuario == null) { return false; }

        LocalDate dataUltimaLeitura = dataUsuario.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataUltimaLeitura, hoje);

        return dias > 1;
    }
}


