package br.com.ernanilima.jmercado;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class JMercado extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        System.out.println("INIT DA APLICACAO");
        context = new SpringApplicationBuilder(br.com.ernanilima.jmercado.Main.class).run();
    }

    @Override
    public void start(Stage stage) {
        System.out.println("INICIANDO APLICACAO");
        context.publishEvent(new StagePrincipal(stage));
    }

    static class StagePrincipal extends ApplicationEvent {
        StagePrincipal(Stage stage) { super(stage); }
        Stage getStagePrincipal() {
            return ((Stage) getSource());
        }
    }
}
