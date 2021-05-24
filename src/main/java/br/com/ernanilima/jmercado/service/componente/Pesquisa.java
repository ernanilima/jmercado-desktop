package br.com.ernanilima.jmercado.service.componente;

import br.com.ernanilima.jmercado.model.*;
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
     * @param tabela TableView<Grupo> - tabela para pesquisa
     * @param campoPesquisar TextField - campo com texto para pesquisa */
    public void pesquisaGrupo(ComboBox<String> cbbxPesquisar, TableView<Grupo> tabela, TextField campoPesquisar) {
        if (!campoPesquisar.isFocused()) {campoPesquisar.requestFocus(); return;}
        FilteredList<Grupo> lista = new FilteredList<>(tabela.getItems(), p -> true);

        String colunaParaPesquisar = cbbxPesquisar.getSelectionModel().getSelectedItem();
        tabela.getSortOrder().clear();
        String pesquisa = campoPesquisar.getText();

        if (!pesquisa.equals("")) {
            lista.setPredicate(model -> {
                if (Coluna.ProdGrupo.CODIGO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa);

                } else if (Coluna.ProdGrupo.DESCRICAO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getDescricao().contains(pesquisa);

                } else if (Coluna.ProdGrupo.CODIGO_DEPARTAMENTO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getMDepartamento().getCodigo()).contains(pesquisa);

                } else if (Coluna.ProdGrupo.DESCRICAO_DEPARTAMENTO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getMDepartamento().getDescricao().contains(pesquisa);

                } else if (Coluna.GERAL.equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa) ||
                            model.getDescricao().contains(pesquisa) ||
                            String.valueOf(model.getMDepartamento().getCodigo()).contains(pesquisa) ||
                            model.getMDepartamento().getDescricao().contains(pesquisa);
                } return false;
            });
        }

        SortedList<Grupo> listaFiltrada = new SortedList<>(lista);
        listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());

        tabela.getItems().setAll(listaFiltrada);
        tabela.getSelectionModel().selectFirst();
    }

    /** Realiza pesquisa na tabela
     * @param cbbxPesquisar ComboBox<String> - combobox da pesquisa
     * @param tabela TableView<Subgrupo> - tabela para pesquisa
     * @param campoPesquisar TextField - campo com texto para pesquisa */
    public void pesquisaSubgrupo(ComboBox<String> cbbxPesquisar, TableView<Subgrupo> tabela, TextField campoPesquisar) {
        if (!campoPesquisar.isFocused()) {campoPesquisar.requestFocus(); return;}
        FilteredList<Subgrupo> lista = new FilteredList<>(tabela.getItems(), p -> true);

        String colunaParaPesquisar = cbbxPesquisar.getSelectionModel().getSelectedItem();
        tabela.getSortOrder().clear();
        String pesquisa = campoPesquisar.getText();

        if (!pesquisa.equals("")) {
            lista.setPredicate(model -> {
                if (Coluna.ProdSubgrupo.CODIGO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa);

                } else if (Coluna.ProdSubgrupo.DESCRICAO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getDescricao().contains(pesquisa);

                } else if (Coluna.ProdSubgrupo.CODIGO_GRUPO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getMGrupo().getCodigo()).contains(pesquisa);

                } else if (Coluna.ProdSubgrupo.DESCRICAO_GRUPO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getMGrupo().getDescricao().contains(pesquisa);

                } else if (Coluna.ProdSubgrupo.CODIGO_DEPARTAMENTO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getMGrupo().getCodigo()).contains(pesquisa);

                } else if (Coluna.ProdSubgrupo.DESCRICAO_DEPARTAMENTO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getMGrupo().getDescricao().contains(pesquisa);

                } else if (Coluna.GERAL.equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa) ||
                            model.getDescricao().contains(pesquisa) ||
                            String.valueOf(model.getMGrupo().getCodigo()).contains(pesquisa) ||
                            model.getMGrupo().getDescricao().contains(pesquisa) ||
                            String.valueOf(model.getMDepartamento().getCodigo()).contains(pesquisa) ||
                            model.getMDepartamento().getDescricao().contains(pesquisa);
                } return false;
            });
        }

        SortedList<Subgrupo> listaFiltrada = new SortedList<>(lista);
        listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());

        tabela.getItems().setAll(listaFiltrada);
        tabela.getSelectionModel().selectFirst();
    }

    /** Realiza pesquisa na tabela
     * @param cbbxPesquisar ComboBox<String> - combobox da pesquisa
     * @param tabela TableView<Produto> - tabela para pesquisa
     * @param campoPesquisar TextField - campo com texto para pesquisa */
    public void pesquisaProduto(ComboBox<String> cbbxPesquisar, TableView<Produto> tabela, TextField campoPesquisar) {
        if (!campoPesquisar.isFocused()) {campoPesquisar.requestFocus(); return;}
        FilteredList<Produto> lista = new FilteredList<>(tabela.getItems(), p -> true);

        String colunaParaPesquisar = cbbxPesquisar.getSelectionModel().getSelectedItem();
        tabela.getSortOrder().clear();
        String pesquisa = campoPesquisar.getText();

        if (!pesquisa.equals("")) {
            lista.setPredicate(model -> {
                if (Coluna.Produto.CODIGO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa);

                } else if (Coluna.Produto.DESCRICAO_PRODUTO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getDescricao().contains(pesquisa);

                } else if (Coluna.Produto.CODIGO_BARRAS.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigoBarras()).contains(pesquisa);

                } else if (Coluna.Produto.CODIGO_SUBGRUPO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getMSubgrupo().getCodigo()).contains(pesquisa);

                } else if (Coluna.Produto.DESCRICAO_SUBGRUPO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getMSubgrupo().getDescricao().contains(pesquisa);

                } else if (Coluna.Produto.PRECO_DE_VENDA.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getMPreco().getPrecoVenda()).contains(pesquisa);

                } else if (Coluna.GERAL.equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa) ||
                            model.getDescricao().contains(pesquisa) ||
                            String.valueOf(model.getCodigoBarras()).contains(pesquisa) ||
                            String.valueOf(model.getMSubgrupo().getCodigo()).contains(pesquisa) ||
                            model.getMSubgrupo().getDescricao().contains(pesquisa) ||
                            String.valueOf(model.getMPreco().getPrecoVenda()).contains(pesquisa);
                } return false;
            });
        }

        SortedList<Produto> listaFiltrada = new SortedList<>(lista);
        listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());

        tabela.getItems().setAll(listaFiltrada);
        tabela.getSelectionModel().selectFirst();
    }

    /** Realiza pesquisa na tabela
     * @param cbbxPesquisar ComboBox<String> - combobox da pesquisa
     * @param tabela TableView<GrupoUsuario> - tabela para pesquisa
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
                } return false;
            });
        }

        SortedList<GrupoUsuario> listaFiltrada = new SortedList<>(lista);
        listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());

        tabela.getItems().setAll(listaFiltrada);
        tabela.getSelectionModel().selectFirst();
    }

    /** Realiza pesquisa na tabela
     * @param cbbxPesquisar ComboBox<String> - combobox da pesquisa
     * @param tabela TableView<Usuario> - tabela para pesquisa
     * @param campoPesquisar TextField - campo com texto para pesquisa */
    public void pesquisaUsuario(ComboBox<String> cbbxPesquisar, TableView<Usuario> tabela, TextField campoPesquisar) {
        if (!campoPesquisar.isFocused()) {campoPesquisar.requestFocus(); return;}
        FilteredList<Usuario> lista = new FilteredList<>(tabela.getItems(), p -> true);

        String colunaParaPesquisar = cbbxPesquisar.getSelectionModel().getSelectedItem();
        tabela.getSortOrder().clear();
        String pesquisa = campoPesquisar.getText();

        if (!pesquisa.equals("")) {
            lista.setPredicate(model -> {
                if (Coluna.Usuario.CODIGO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa);

                } else if (Coluna.Usuario.NOME_COMPLETO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getNomeCompleto().contains(pesquisa);

                } else if (Coluna.Usuario.NOME_SISTEMA.getColuna().equals(colunaParaPesquisar)) {
                    return model.getNomeSistema().contains(pesquisa);

                } else if (Coluna.Usuario.CODIGO_GRUPOUSUARIO.getColuna().equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getMGrupoUsuario().getCodigo()).contains(pesquisa);

                } else if (Coluna.Usuario.DESCRICAO_GRUPOUSUARIO.getColuna().equals(colunaParaPesquisar)) {
                    return model.getMGrupoUsuario().getDescricao().contains(pesquisa);

                } else if (Coluna.Usuario.BLOQUEADO.getColuna().equals(colunaParaPesquisar)) {
                    // rever forma de simplificar
                    if (pesquisa.toLowerCase().contains("s")) {
                        return model.getBloqueado().toString().contains("t");
                    } else if (pesquisa.toLowerCase().contains("n")) {
                        return model.getBloqueado().toString().contains("f");
                    }
                } else if (Coluna.GERAL.equals(colunaParaPesquisar)) {
                    return String.valueOf(model.getCodigo()).contains(pesquisa) ||
                            model.getNomeCompleto().contains(pesquisa) ||
                            model.getNomeSistema().contains(pesquisa) ||
                            String.valueOf(model.getMGrupoUsuario().getCodigo()).contains(pesquisa) ||
                            model.getMGrupoUsuario().getDescricao().contains(pesquisa);
                } return false;
            });
        }

        SortedList<Usuario> listaFiltrada = new SortedList<>(lista);
        listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());

        tabela.getItems().setAll(listaFiltrada);
        tabela.getSelectionModel().selectFirst();
    }
}
