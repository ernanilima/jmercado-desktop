package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.service.DepartamentoService;
import br.com.ernanilima.jmercado.service.validacao.ValidarCampo;
import br.com.ernanilima.jmercado.service.validacao.ValidarCodigo;
import br.com.ernanilima.jmercado.utils.Utils;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class DepartamentoController implements Initializable {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;

    @Autowired private DepartamentoService sDepartamento;

    @Autowired private Utils utils;
    @Autowired private ValidarCodigo vCodigo;
    @Autowired private ValidarCampo vCampo;

    @Value("classpath:/fxml/cad_departamento.fxml")
    private Resource R_FXML;

    @FXML private AnchorPane painel;
    @FXML private Label campoTitulo;
    @FXML private TabPane tab;
    @FXML private Tab tpListar;
    @FXML private TextField campoPesquisar;
    @FXML private ComboBox<?> cbbxPesquisar;
    @FXML private Button btnPesquisar;
    @FXML private Button btnSelecionar;
    @FXML private Button btnCadastrar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private AnchorPane boxLista;
    @FXML private TableView<Departamento> tabela;
    @FXML private Tab tpCadastrar;
    @FXML private AnchorPane box;
    @FXML private Label textoCampoCodigo;
    @FXML private TextField campoCodigo;
    @FXML private Button btnGravar;
    @FXML private Button btnCancelar;
    @FXML private Label textoCampoDescricao;
    @FXML private TextField campoDescricao;

    private ObservableList<Departamento> oListDepartamento;
    private List<Departamento> lsDepartamento;
    private TableColumn<Departamento, Integer> colunaCodigo;
    private TableColumn<Departamento, String> colunaDescricao;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        STAGE = new Stage();
        oListDepartamento = FXCollections.observableArrayList();

        //ACOES EM BOTOES
        btnCadastrar.setOnAction(e -> cadastrar());
        btnEditar.setOnAction(e -> editar());
        btnExcluir.setOnAction(e -> excluir());
        btnGravar.setOnAction(e -> gravar());
        btnCancelar.setOnAction(e -> cancelar());

        carregarEstruturaTabela();
    }

    private void carregarEstruturaTabela() {

        //Exibi texto na tabela caso ela esteja vazia
        tabela.setPlaceholder(new Label(""));

        colunaCodigo = new TableColumn<>("CÓDIGO");
        colunaDescricao = new TableColumn<>("DESCRIÇÃO");

        colunaCodigo.setMinWidth(85);
        colunaCodigo.setMaxWidth(colunaCodigo.getMinWidth());

        // permite selecionar celular ou linha inteira
        tabela.getSelectionModel().setCellSelectionEnabled(true);
        // adiciona botao que permite escolher colunas
        tabela.setTableMenuButtonVisible(true);

        // ajusta descricao
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabela.getColumns().add(colunaCodigo);
        tabela.getColumns().add(colunaDescricao);

        colunaCodigo.setCellValueFactory(model -> new SimpleObjectProperty<>(model.getValue().getCodigo()));
        colunaDescricao.setCellValueFactory(model -> new SimpleObjectProperty<>(model.getValue().getDescricao()));

    }

    private void carregarConteudoTabela() {
        oListDepartamento.clear();
        sDepartamento.listarTudoAsinc().thenAccept(list -> Platform.runLater(()-> {
            lsDepartamento = list;
            tabela.getSortOrder().clear();
            oListDepartamento.addAll(lsDepartamento);
            tabela.getItems().setAll(oListDepartamento);
            tabela.requestFocus();
        }));
    }

    /** Cadastrar novo */
    public void cadastrar() {
        cInicio.setTitulo(campoTitulo, "Cadastrar Departamento");
        utils.exibirAba(tab, tpCadastrar, tpListar);
        campoCodigo.requestFocus();
    }

    private void editar() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            Departamento mDepartamento = tabela.getItems().get(linhaSelecionada);
            campoCodigo.setDisable(true);
            campoCodigo.setText(String.valueOf(mDepartamento.getCodigo()));
            campoDescricao.setText(mDepartamento.getDescricao());

            cInicio.setTitulo(campoTitulo, "Editar Departamento");
            utils.exibirAba(tab, tpCadastrar, tpListar);
            campoDescricao.requestFocus();
        }
    }

    private void excluir() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            sDepartamento.remover(tabela.getItems().get(linhaSelecionada));
            carregarConteudoTabela();
            tabela.getSelectionModel().select(linhaSelecionada > 0 ? linhaSelecionada - 1 : 0);
        }
    }

    private void gravar() {
        if (validarCampos()) {
            Departamento mDepartamento = new Departamento(
                    Integer.parseInt(campoCodigo.getText()),
                    campoDescricao.getText()
            );

            sDepartamento.gravar(mDepartamento);
            limpar();
            carregarConteudoTabela();
            cInicio.setTitulo(campoTitulo, "Lista De Departamentos");
            utils.exibirAba(tab, tpListar, tpCadastrar);
        }
    }

    private void cancelar() {
        cInicio.setTitulo(campoTitulo, "Lista De Departamentos");
        utils.exibirAba(tab, tpListar, tpCadastrar);
    }

    private void limpar() {
        utils.limparCampos(campoPesquisar, campoCodigo, campoDescricao);
    }

    private boolean validarCampos() {
        return vCampo.campoVazio(campoCodigo, textoCampoCodigo) &&
                vCampo.campoVazio(campoDescricao, textoCampoDescricao) &&
                !vCodigo.novo(campoCodigo, sDepartamento);
    }

    /** Obtem o painel para ser usado internamente.
     * @return AnchorPane - painel para usar internamente */
    public AnchorPane getPainel() {
        construirPainel();
        btnSelecionar.setVisible(false);
        campoTitulo.setVisible(false);
        campoTitulo.setPrefHeight(0);
        cInicio.setTitulo(campoTitulo, "Lista De Departamentos");
        return painel;
    }

    /** Constroi o painel para ser usado internamente */
    private void construirPainel() {
        try {
            if (STAGE == null) {
                LOADER = new FXMLLoader(R_FXML.getURL());
                LOADER.setController(this);
                ROOT = LOADER.load();
                STAGE.initModality(Modality.APPLICATION_MODAL);
                LOADER.setControllerFactory(aClass -> springContext.getBean(aClass));
                carregarConteudoTabela();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
