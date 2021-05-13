package br.com.ernanilima.jmercado;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Inicio implements ApplicationListener<JMercado.StagePrincipal> {

    @Override
    public void onApplicationEvent(JMercado.StagePrincipal event) {
        System.out.println(event.getStagePrincipal());
    }
}
