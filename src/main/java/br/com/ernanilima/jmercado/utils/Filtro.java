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
}
