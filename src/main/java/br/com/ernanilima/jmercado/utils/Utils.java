package br.com.ernanilima.jmercado.utils;

import javafx.scene.Node;
import javafx.scene.control.*;
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

    /** Limpa o(s) object(s) passado(s) no parametro
     * @param node - campo(s) */
    public void limparCampos(Node... node) {
        for (Node nodes : node) {
            List<Node> lsNodes = Collections.singletonList(nodes);
            for (Node n : lsNodes) {
                n.setDisable(false);
                if (n instanceof TextField)
                    ((TextField) n).setText("");
                if (n instanceof PasswordField)
                    ((PasswordField) n).setText("");
                if (n instanceof ComboBox<?>)
                    ((ComboBox) n).getSelectionModel().select(0);
                if (n instanceof CheckBox)
                    ((CheckBox) n).setSelected(false);
            }
        }
    }

    /** Valida o campo passado no parametro
     * @param campo - campo a ser validado
     * @param tituloCampo - titulo do campo a ser validado
     * @return boolean - true se tudo ok com o campo passado no parametro */
    public boolean vCampos(Node campo, Label tituloCampo) {
        String s = "";
        if (campo instanceof TextField) {
            s = ((TextField) campo).getText();
        }
        if (campo instanceof ComboBox<?>) {
            ComboBox<?> cbbx = (ComboBox) campo;
            s = String.valueOf(cbbx.getSelectionModel().getSelectedIndex());
        }
        return !s.equals("") && !s.equals("0");
    }
}
