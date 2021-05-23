package br.com.ernanilima.jmercado.utils;

public class Filtro {

    /** Possibilita que filtre conteudo para numero inteiro
     * Se nulo ou vazio, retorna -1
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
     * Se nulo ou vazio, retorna -1
     * @param numeroLong String - conteudo para filtrar
     * @return long - conteudo filtrado para numero long */
    public static long pLong(String numeroLong) {
        if (numeroLong == null) { return -1; }
        numeroLong = numeroLong.replaceAll("[^0-9]", "");
        if (numeroLong.equals("")) { return -1; }
        return Long.parseLong(numeroLong);
    }

    /** Possibilita que filtre conteudo para numero double
     * Usado principalmente para preco
     * Converte todas as virgulas(,) para pontos(.)
     * Se nulo ou vazio, retorna 0.00
     * @param numeroDouble String - Conteudo a ser filtrado
     * @return double - conteudo filtrado para double */
    public static Double pDouble(String numeroDouble) {
        if (numeroDouble == null) { return 0.00; }
        numeroDouble = numeroDouble.replaceAll("[^0-9|^,]", "").replace(",", ".");
        if (numeroDouble.equals("")) { return 0.00; }
        return Double.parseDouble(numeroDouble);
    }
}
