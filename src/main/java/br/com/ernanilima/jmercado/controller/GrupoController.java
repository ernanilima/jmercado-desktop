package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.listener.FocusListener;
import br.com.ernanilima.jmercado.controller.listener.KeyListener;
import br.com.ernanilima.jmercado.model.Grupo;
import br.com.ernanilima.jmercado.service.GrupoService;
import br.com.ernanilima.jmercado.service.componente.Mascara;
import br.com.ernanilima.jmercado.service.constante.Mensagem;
import br.com.ernanilima.jmercado.service.constante.enums.Coluna;
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
public class GrupoController implements Initializable, ICadastro {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;
    @Autowired private FocusListener lFocus;
    @Autowired private KeyListener lKey;
    @Autowired private GrupoService sGrupo;
    @Autowired private Utils utils;

    @Value("classpath:/fxml/cad_grupo.fxml")
    private Resource R_FXML;
    @Value("classpath:/css/modal.css")
    private Resource R_CSS;

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
    @FXML private TableView<Grupo> tabela;
    @FXML private Tab tpCadastrar;
    @FXML private AnchorPane box;
    @FXML private Label textoCampoCodigo;
    @FXML private TextField campoCodigo;
    @FXML private Button btnGravar;
    @FXML private Button btnCancelar;
    @FXML private Label textoCampoDescricao;
    @FXML private TextField campoDescricao;
    @FXML private Label textoCampoDepartamento;
    @FXML private TextField campoCodDepartamento;
    @FXML private Button btnBuscar;
    @FXML private TextField campoDescricaoDepartamento;

    private ObservableList<Grupo> oListGrupo;
    private List<Grupo> lsGrupo;
    private TableColumn<Grupo, Integer> colunaCodigo;
    private TableColumn<Grupo, String> colunaDescricao;
    private TableColumn<Grupo, Integer> colunaCodigoDepartamento;
    private TableColumn<Grupo, String> colunaDescricaoDepartamento;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
        oListGrupo = FXCollections.observableArrayList();

        //ACOES EM BOTOES
        btnPesquisar.setOnAction(e -> pesquisar());
        btnCadastrar.setOnAction(e -> cadastrar());
        btnEditar.setOnAction(e -> editar());
        btnExcluir.setOnAction(e -> excluir());
        btnGravar.setOnAction(e -> gravar());
        btnCancelar.setOnAction(e -> cancelar());
        btnBuscar.setOnAction(e -> buscar());

        // ACOES DE FOCO
        campoPesquisar.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.PESQUISA));
        tabela.getFocusModel().focusedCellProperty().addListener(lFocus.tabelaActionListener(tabela, Coluna.ProdGrupo.getColunasLegendas()));
        campoCodigo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdGrupo.CODIGO));
        campoDescricao.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdGrupo.DESCRICAO));
        campoCodDepartamento.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdDepartamento.CODIGO));
        campoDescricaoDepartamento.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdDepartamento.DESCRICAO));

        // ACOES AO PRESSIONAR TECLAS
        campoPesquisar.setOnKeyPressed(lKey.campoPesquisaKeyPressed(tabela, this));
        painel.setOnKeyReleased(lKey.atalhoKeyReleased(this));

        // MASCARAS EM CAMPOS
        Mascara.textoNumeroMaiusculo(campoPesquisar, 50);
        Mascara.numeroInteiro(campoCodigo, 3);
        Mascara.textoNumeroMaiusculo(campoDescricao, 50);
        Mascara.numeroInteiro(campoCodDepartamento, 3);

        // EXIBE A ABA PRINCIPAL E DESABILITA AS OUTRAS
        utils.exibirAba(tab, tpListar, tpCadastrar);

        carregarEstruturaTabela();
        carregarOpcoesPesquisa();
    }

    private void carregarEstruturaTabela() {
        //Exibi texto na tabela caso ela esteja vazia
        tabela.setPlaceholder(new Label(""));

        colunaCodigo = new TableColumn<>(Coluna.ProdGrupo.CODIGO.getColuna());
        colunaDescricao = new TableColumn<>(Coluna.ProdGrupo.DESCRICAO.getColuna());
        colunaCodigoDepartamento = new TableColumn<>(Coluna.ProdGrupo.CODIGO_DEPARTAMENTO.getColuna());
        colunaDescricaoDepartamento = new TableColumn<>(Coluna.ProdGrupo.DESCRICAO_DEPARTAMENTO.getColuna());

        colunaCodigo.setMinWidth(85);
        colunaCodigo.setMaxWidth(colunaCodigo.getMinWidth());
        colunaCodigoDepartamento.setMinWidth(100);
        colunaCodigoDepartamento.setMaxWidth(colunaCodigoDepartamento.getMinWidth());
        colunaDescricaoDepartamento.setMinWidth(210);
        colunaDescricaoDepartamento.setMaxWidth(colunaDescricaoDepartamento.getMinWidth());

        // permite selecionar celular ou linha inteira
        tabela.getSelectionModel().setCellSelectionEnabled(true);
        // adiciona botao que permite escolher colunas
        tabela.setTableMenuButtonVisible(true);

        // ajusta descricao
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabela.getColumns().add(colunaCodigo);
        tabela.getColumns().add(colunaDescricao);
        tabela.getColumns().add(colunaCodigoDepartamento);
        tabela.getColumns().add(colunaDescricaoDepartamento);

        colunaCodigo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCodigo()));
        colunaDescricao.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescricao()));
        colunaCodigoDepartamento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMDepartamento().getCodigo()));
        colunaDescricaoDepartamento.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMDepartamento().getDescricao()));
    }

    private void carregarConteudoTabela() {
        oListGrupo.clear();
        sGrupo.listarTudoAsinc().thenAccept(list -> Platform.runLater(()-> {
            lsGrupo = list;
            tabela.getSortOrder().clear();
            oListGrupo.addAll(lsGrupo);
            tabela.getItems().setAll(oListGrupo);
            tabela.requestFocus();
        }));
    }

    /** Carrega as opcoes para pesquisa no combobox */
    private void carregarOpcoesPesquisa() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        List<String> list = Coluna.ProdDepartamento.getColunas();
        oList.clear();

        oList.add(Coluna.GERAL);
        oList.addAll(list);

        cbbxPesquisar.setItems(oList);
        cbbxPesquisar.getSelectionModel().selectFirst();
        cbbxPesquisar.setVisibleRowCount(9);
    }

    @Override
    public void pesquisar() {

    }

    @Override
    public void cadastrar() {

    }

    @Override
    public void editar() {

    }

    @Override
    public void excluir() {

    }

    @Override
    public void gravar() {

    }

    @Override
    public void cancelar() {

    }

    private void buscar() {

    }

    /** Obtem o painel para ser usado internamente.
     * @return AnchorPane - painel para usar internamente */
    public AnchorPane getPainel() {
        construirPainel();
        btnSelecionar.setVisible(false);
        campoTitulo.setVisible(false);
        campoTitulo.setPrefHeight(0);
        cInicio.setTitulo(campoTitulo, "Lista De Grupos De Produtos");
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
                ROOT.getStylesheets().add(R_CSS.getURL().toExternalForm());
                carregarConteudoTabela();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
