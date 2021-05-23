package br.com.ernanilima.jmercado.service.componente;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class Mascara {

    /** Faz com que campo aceite apenas numeros
     * @param campo TextField - campo que deve ter o filtro
     * @param qtdCaracteres int - quantidade de caracteres aceitos no campo */
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
     * @param qtdCaracteres int - quantidade de caracteres aceitos no campo */
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

    /** Faz com que campo aceitar apenas valor monetario
     * @param campo TextField - campo que deve ter o filtro
     * @param qtdDecimais int - quantidade de casas decimais */
    public static void numeroMonetario(final TextField campo, final int qtdDecimais) {
        numeroMonetario(campo, qtdDecimais, 11);
    }

    /** Faz com que campo aceitar apenas valor monetario
     * @param campo TextField - campo que deve ter o filtro
     * @param qtdDecimais int - quantidade de casas decimais
     * @param qtdCaracteres qtdCaracteres - quantidade de caracteres aceitos no campo */
    public static void numeroMonetario(final TextField campo, final int qtdDecimais, final int qtdCaracteres) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                String valor = campo.getText();
                valor = valor.replaceAll("[^0-9]", "");
                valor = valor.replaceAll("([0-9]{1})([0-9]{"+ (qtdDecimais+6) +"})$", "$1.$2");
                valor = valor.replaceAll("([0-9]{1})([0-9]{"+ (qtdDecimais+3) +"})$", "$1.$2");
                valor = valor.replaceAll("([0-9]{1})([0-9]{"+ qtdDecimais +"})$", "$1,$2");
                campo.setText(valor);
                campo.positionCaret(campo.getText().length());
            });

            if (newValue.length() > qtdCaracteres) {
                campo.setText(oldValue);
            }
        });
    }
}
