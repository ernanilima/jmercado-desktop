package br.com.ernanilima.jmercado;

import br.com.ernanilima.jmercado.controller.LoginController;
import br.com.ernanilima.jmercado.utils.Temporario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Inicio implements ApplicationListener<JMercado.StagePrincipal> {

    @Autowired private LoginController cLogin;
    @Autowired private Temporario temporario;

    @Override
    public void onApplicationEvent(JMercado.StagePrincipal event) {
        temporario.criarBancoDeDados();
        cLogin.exibir(event.getStagePrincipal());
    }
}
