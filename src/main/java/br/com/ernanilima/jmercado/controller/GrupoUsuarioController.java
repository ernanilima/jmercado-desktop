package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.model.GrupoUsuario;
import br.com.ernanilima.jmercado.service.GrupoUsuarioService;
import br.com.ernanilima.jmercado.service.constante.enums.Coluna;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class GrupoUsuarioController implements Initializable {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;
    @Autowired private GrupoUsuarioService sGrupoUsuario;

    @Value("classpath:/fxml/cad_grupousuario.fxml")
    private Resource R_FXML;

    @FXML private AnchorPane painel;
    @FXML private Label campoTitulo;
    @FXML private TabPane tab;
    @FXML private Tab tpListar;
    @FXML private TextField campoPesquisar;
    @FXML private ComboBox<String> cbbxPesquisar;
    @FXML private Button btnPesquisar;
    @FXML private Button btnSelecionar;
    @FXML private Button btnCadastrar;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;
    @FXML private AnchorPane boxLista;
    @FXML private TableView<GrupoUsuario> tabela;
    @FXML private Tab tpCadastrar;
    @FXML private AnchorPane box;
    @FXML private Label textoCampoCodigo;
    @FXML private TextField campoCodigo;
    @FXML private Button btnIgualar;
    @FXML private Button btnGravar;
    @FXML private Button btnCancelar;
    @FXML private Label textoCampoDescricao;
    @FXML private TextField campoDescricao;
    @FXML private TreeView<?> treeLiberacao;

    private ObservableList<GrupoUsuario> oListGrupoUsu;
    private List<GrupoUsuario> lsGrupoUsu;
    private TableColumn<GrupoUsuario, Integer> colunaCodigo;
    private TableColumn<GrupoUsuario, String> colunaDescricao;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
        oListGrupoUsu = FXCollections.observableArrayList();

        carregarEstruturaTabela();
        carregarOpcoesPesquisa();
    }

    private void carregarEstruturaTabela() {

        //Exibi texto na tabela caso ela esteja vazia
        tabela.setPlaceholder(new Label(""));

        colunaCodigo = new TableColumn<>(Coluna.GrupoUsuario.CODIGO.getColuna());
        colunaDescricao = new TableColumn<>(Coluna.GrupoUsuario.DESCRICAO.getColuna());

        colunaCodigo.setMinWidth(85);
        colunaCodigo.setMaxWidth(colunaCodigo.getMinWidth());

        tabela.getSelectionModel().setCellSelectionEnabled(true);
        tabela.setTableMenuButtonVisible(true);

        // ajusta descricao
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabela.getColumns().add(colunaCodigo);
        tabela.getColumns().add(colunaDescricao);

        colunaCodigo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCodigo()));
        colunaDescricao.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescricao()));

    }

    private void carregarConteudoTabela() {
        oListGrupoUsu.clear();
        sGrupoUsuario.listarTudoAsinc().thenAccept(list -> Platform.runLater(()-> {
            lsGrupoUsu = list;
            tabela.getSortOrder().clear();
            oListGrupoUsu.addAll(lsGrupoUsu);
            tabela.getItems().setAll(oListGrupoUsu);
            tabela.requestFocus();
        }));
    }

    private void carregarOpcoesPesquisa() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        List<String> list = Coluna.GrupoUsuario.getColunas();
        oList.clear();

        oList.add(Coluna.GERAL);
        oList.addAll(list);

        cbbxPesquisar.setItems(oList);
        cbbxPesquisar.getSelectionModel().selectFirst();
        cbbxPesquisar.setVisibleRowCount(9);
    }

    /** Obtem o painel para ser usado internamente.
     * @return AnchorPane - painel para usar internamente */
    public AnchorPane getPainel() {
        construirPainel();
        btnSelecionar.setVisible(false);
        campoTitulo.setVisible(false);
        campoTitulo.setPrefHeight(0);
        cInicio.setTitulo(campoTitulo, "Lista De Grupos De Usuários");
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
