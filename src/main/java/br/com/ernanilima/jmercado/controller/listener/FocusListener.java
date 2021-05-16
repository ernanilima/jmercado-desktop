package br.com.ernanilima.jmercado.controller.listener;

import br.com.ernanilima.jmercado.service.componente.Legenda;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FocusListener {

    @Autowired Legenda legenda;

    /** Exibe legenda da coluna selecionada
     * @param tabela TableView - tabela selecionada
     * @param list String[][] - lista de colunas e legendas
     * @return ChangeListener<TablePosition> */
    public ChangeListener<TablePosition> tabelaActionListener(TableView tabela, String[][] list) {
        return (observableValue, tablePosition, t1) -> {
            // verifica se alguma coluna foi selecionada
            if (tabela.getFocusModel().getFocusedCell().getColumn() != -1) {

                // pega o nome da coluna selecionada
                String colunaSelecionada = tabela.getFocusModel().getFocusedCell().getTableColumn().getText();

                for (String[] strings : list) {

                    // verifica se o nome da coluna selecionada
                    // eh igual o nome da coluna passada no parametro
                    if (colunaSelecionada.equals(strings[0])) {

                        // exibe a legenda da coluna selecionada
                        this.legenda.exibirLegenda(strings[1]);
                    }
                }
            }
        };
    }

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
