package br.com.ernanilima.jmercado.utils;

public class Filtro {
    public static Integer pInt(String numeroInt) {
        if (numeroInt == null || numeroInt.equals("")) { return -1; }
        numeroInt = numeroInt.replaceAll("[^0-9]", "");
        return Integer.parseInt(numeroInt);
    }
}
