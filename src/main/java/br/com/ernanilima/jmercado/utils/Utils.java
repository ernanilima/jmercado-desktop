package br.com.ernanilima.jmercado.utils;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class Utils {

    /** Utilizado para exibir apenas a aba necessaria e inibir as demais
     * @param tabPane - tabpane principal, onde eh necessario manipular
     * @param exibir - aba que percisa ser exibida
     * @param inibir - aba(s) para inibir */
    public void exibirAba(TabPane tabPane, Tab exibir, Tab... inibir) {
        for (Tab tabs : inibir) {
            List<Tab> lsTabs = Collections.singletonList(tabs);
            lsTabs.stream().filter(Objects::nonNull).forEach(tab -> tab.setDisable(true));
        }

        exibir.setDisable(false);
        tabPane.getSelectionModel().select(exibir);
    }
}
