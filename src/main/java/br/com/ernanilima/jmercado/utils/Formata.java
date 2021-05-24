package br.com.ernanilima.jmercado.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Formata {

    /** Formata valores monetarios com o padrao do Brasil.
     * Arredondamento para cima, valores com duas casas decimais */
    public static final DecimalFormat VALOR_RS = new DecimalFormat("###0.00",
            new DecimalFormatSymbols(new Locale("pt", "BR")));
    static { VALOR_RS.setRoundingMode(RoundingMode.HALF_UP); }
}
