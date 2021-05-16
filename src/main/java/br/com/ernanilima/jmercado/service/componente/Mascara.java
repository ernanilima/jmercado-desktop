package br.com.ernanilima.jmercado.service.componente;

import javafx.scene.control.TextField;

public class Mascara {

    /** Faz com que campo aceite apenas numeros
     * @param campo TextField - campo que deve ter o filtro
     * @param qtdCaracteres qtdCaracteres - quantidade de caracteres aceitos no campo */
    public static void numeroInteiro(TextField campo, int qtdCaracteres) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {

            // formata o novo texto para aceitar apenas numeros
            String novoTexto = newValue.replaceAll("[^0-9]", "");

            // verifica se o novo texto tem a quantidade de caracteres
            if (novoTexto.length() <= qtdCaracteres) {
                campo.setText(novoTexto);

            }

            // se tiver uma quantidade maior que a quantidade de caracteres
            else {
                campo.setText(oldValue);
            }
        });
    }

    /** Faz com que campo aceitar apenas letras e numeros
     * Converte as letras para maiusculo
     * @param campo TextField - campo que deve ter o filtro
     * @param qtdCaracteres qtdCaracteres - quantidade de caracteres aceitos no campo */
    public static void textoNumeroMaiusculo(TextField campo, int qtdCaracteres) {
        campo.textProperty().addListener((observableValue, oldValue, newValue) -> {

            // formata o novo texto para aceitar letras e numeros
            // converte tudo para maiusculo
            String novoTexto = newValue.toUpperCase().replaceAll("[^A-Za-z0-9- ]", "");

            // verifica se o novo texto tem a quantidade de caracteres
            if (novoTexto.length() <= qtdCaracteres) {
                campo.setText(novoTexto);

            }

            // se tiver uma quantidade maior que a quantidade de caracteres
            else {
                campo.setText(oldValue);
            }
        });
    }
}
