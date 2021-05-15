package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.model.Departamento;
import br.com.ernanilima.jmercado.repository.DepartamentoRepository;
import br.com.ernanilima.jmercado.service.DepartamentoService;
import br.com.ernanilima.jmercado.utils.Utils;
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
        lsDepartamento = sDepartamento.listarTudo();

        tabela.getSortOrder().clear();
        oListDepartamento.addAll(lsDepartamento);
        tabela.getItems().setAll(oListDepartamento);
    }

    /** Cadastrar novo */
    public void cadastrar() {
        cInicio.setTitulo(campoTitulo, "Cadastrar Departamento");
        utils.exibirAba(tab, tpCadastrar, tpListar);
        campoCodigo.requestFocus();
    }

    private void editar() {
        System.out.println("EDITAR");
    }

    private void excluir() {
        System.out.println("EXCLUIR");
    }

    private void gravar() {
        System.out.println("GRAVAR");
        cInicio.setTitulo(campoTitulo, "Lista De Departamentos");
        utils.exibirAba(tab, tpListar, tpCadastrar);
    }

    private void cancelar() {
        cInicio.setTitulo(campoTitulo, "Lista De Departamentos");
        utils.exibirAba(tab, tpListar, tpCadastrar);
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
