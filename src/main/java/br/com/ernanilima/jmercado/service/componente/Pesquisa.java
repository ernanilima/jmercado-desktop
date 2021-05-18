package br.com.ernanilima.jmercado.service.componente;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.model.GrupoUsuario;
import br.com.ernanilima.jmercado.service.constante.enums.Coluna;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class Pesquisa {
    // IMPORTANTE: criar uma forma de utilizar a pesquisa em todos

    /** Realiza pesquisa na tabela
     * @param cbbxPesquisar ComboBox<String> - combobox da pesquisa
     * @param tabela TableView<Departamento> - tabela para pesquisa
     * @param campoPesquisar TextField - campo com texto para pesquisa */
    public void pesquisaDepartamento(ComboBox<String> cbbxPesquisar, TableView<Departamento> tabela, TextField campoPesquisar) {
        if (!campoPesquisar.isFocused()) {campoPesquisar.requestFocus(); return;}
        String colunaParaPesquisar = cbbxPesquisar.getSelectionModel().getSelectedItem();

        FilteredList<Departamento> lista = new FilteredList<>(tabela.getItems(), p -> true);
        tabela.getSortOrder().clear();
        String pesquisa = campoPesquisar.getText();

        if (!pesquisa.equals("")) {
            lista.setPredicate(model -> {

                if (Coluna.ProdDepartamento.CODIGO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa);

                } else if (Coluna.ProdDepartamento.DESCRICAO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getDescricao().contains(pesquisa);

                } else if (Coluna.GERAL.equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa) ||
                            model.getDescricao().contains(pesquisa);

                } return false;
            });
        }

        SortedList<Departamento> listaFiltrada = new SortedList<>(lista);
        listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());

        tabela.getItems().setAll(listaFiltrada);
        tabela.getSelectionModel().selectFirst();
    }

    /** Realiza pesquisa na tabela
     * @param cbbxPesquisar ComboBox<String> - combobox da pesquisa
     * @param tabela TableView<Departamento> - tabela para pesquisa
     * @param campoPesquisar TextField - campo com texto para pesquisa */
    public void pesquisaGrupoUsuario(ComboBox<String> cbbxPesquisar, TableView<GrupoUsuario> tabela, TextField campoPesquisar) {
        if (!campoPesquisar.isFocused()) {campoPesquisar.requestFocus(); return;}
        FilteredList<GrupoUsuario> lista = new FilteredList<>(tabela.getItems(), p -> true);

        String colunaParaPesquisar = cbbxPesquisar.getSelectionModel().getSelectedItem();
        tabela.getSortOrder().clear();
        String pesquisa = campoPesquisar.getText();

        if (!pesquisa.equals("")) {
            lista.setPredicate(model -> {

                if (Coluna.GrupoUsuario.CODIGO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa);

                } else if (Coluna.GrupoUsuario.DESCRICAO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getDescricao().contains(pesquisa);

                } else if (Coluna.GERAL.equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa) ||
                            model.getDescricao().contains(pesquisa);

                }
                return false;
            });
        }

        SortedList<GrupoUsuario> listaFiltrada = new SortedList<>(lista);
        listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());

        tabela.getItems().setAll(listaFiltrada);
        tabela.getSelectionModel().selectFirst();
    }
}
