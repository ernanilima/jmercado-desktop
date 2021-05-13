package br.com.ernanilima.jmercado;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    // Classe Main utilizada para que funcione a utilizacao
    // do JavaFX sem precisar baixar as dependencias manualmente.
    // Deve sempre ser iniciado essa classe primeiro.
    public static void main(String[] args) {
        Application.launch(JMercado.class, args);
    }
}
