package br.com.ernanilima.jmercado.utils;

public class Filtro {

    /** Possibilita que filtre conteudo para numero inteiro
     * @param numeroInt String - conteudo para filtrar
     * @return int - conteudo filtrado para numero inteiro */
    public static int pInt(String numeroInt) {
        if (numeroInt == null) { return -1; }
        numeroInt = numeroInt.replaceAll("[^0-9]", "");
        if (numeroInt.equals("")) { return -1; }
        return Integer.parseInt(numeroInt);
    }

    /** Possibilita que filtre conteudo para numero long
     * Usado principalmente para codigo de barras
     * @param numberLong String - conteudo para filtrar
     * @return long - conteudo filtrado para numero long */
    public static long pLong(String numberLong) {
        if (numberLong == null) { return -1; }
        numberLong = numberLong.replaceAll("[^0-9]", "");
        if (numberLong.equals("")) { return -1; }
        return Long.parseLong(numberLong);
    }
}
