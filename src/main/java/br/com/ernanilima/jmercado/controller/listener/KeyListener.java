package br.com.ernanilima.jmercado.controller.listener;

import br.com.ernanilima.jmercado.controller.ICadastro;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.stereotype.Component;

@Component
public class KeyListener {

    /** Executa o metodo da interface
     * @param tabela TableView - tabela para atribuir foco
     * @param iCadastros ICadastro - interface utilizada
     * @return EventHandler<KeyEvent> */
    public EventHandler<KeyEvent> campoPesquisaKeyPressed(TableView tabela, ICadastro iCadastros) {
        return event -> {
            // verifica se a tecla foi pressionada e executa o metodo da interface
            // passada como parametro
            if (event.getCode() == KeyCode.ENTER) {
                iCadastros.pesquisar();

            } else
                // verifica tecla pessionada e foca na tabela, geralmente tabela da pesquisa
                if (event.getCode() == KeyCode.UP | event.getCode() == KeyCode.DOWN) {
                tabela.requestFocus();
            }
        };
    }
}