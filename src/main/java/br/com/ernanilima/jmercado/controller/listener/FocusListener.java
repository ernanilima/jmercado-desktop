package br.com.ernanilima.jmercado.controller.listener;

import br.com.ernanilima.jmercado.service.componente.Legenda;
import javafx.beans.value.ChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FocusListener {

    @Autowired Legenda legenda;

    /** Exibe legenda
     * @param legenda String - legenda que deve ser exibida
     * @return ChangeListener<Boolean> */
    public ChangeListener<Boolean> exibeLegendaActionListener(String legenda) {
        return (observable, oldValue, newValue) -> {
            if (newValue) {
                // exibe legenda se teve foco
                this.legenda.exibirLegenda(legenda);
            } else {
                // remove legenda se nao tiver em foco
                this.legenda.exibirLegenda("");
            }
        };
    }
}
