package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.MontarLiberacoes;
import br.com.ernanilima.jmercado.model.Usuario;
import br.com.ernanilima.jmercado.service.UsuarioService;
import br.com.ernanilima.jmercado.service.constante.enums.Coluna;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
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
import java.util.Set;

@Controller
public class UsuarioController implements Initializable {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;
    @Autowired private UsuarioService sUsuario;

    @Value("classpath:/fxml/cad_usuario.fxml")
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
    @FXML private TableView<Usuario> tabela;
    @FXML private Tab tpCadastrar;
    @FXML private AnchorPane box;
    @FXML private Label textoCampoCodigo1;
    @FXML private TextField campoCodigo;
    @FXML private Button btnGravar;
    @FXML private Button btnCancelar;
    @FXML private Button btnIgualar;
    @FXML private Button btnRemoverSenha;
    @FXML private Label textoCampoNomeCompleto;
    @FXML private CheckBox chbxBloqueado;
    @FXML private TextField campoNomeCompleto;
    @FXML private Label textoCampoNomeSistema;
    @FXML private TextField campoNomeSistema;
    @FXML private Label textoCampoGrupoUsuario;
    @FXML private TextField campoCodGrupoUsuario;
    @FXML private Button btnBuscar;
    @FXML private TextField campoDescricaoGrupoUsuario;
    @FXML private RadioButton btnRLiberacaoUsuario;
    @FXML private ToggleGroup grupoTipoLiberacao;
    @FXML private RadioButton btnRLiberacaoGrupo;
    @FXML private TreeView<Liberacoes> treeLiberacao;

    private ObservableList<Usuario> oListUsuario;
    private List<Usuario> lsUsuario;
    private TableColumn<Usuario, Integer> colunaCodigo;
    private TableColumn<Usuario, String> colunaNomeCompleto;
    private TableColumn<Usuario, String> colunaNomeSistema;
    private TableColumn<Usuario, Integer> colunaCodigoGrupoUsuario;
    private TableColumn<Usuario, String> colunaDescricaoGrupoUsuario;
    private TableColumn<Usuario, String> colunaStatus;

    private Set<TreeItem<Liberacoes>> TREE_SELECIONADO;
    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
        oListUsuario = FXCollections.observableArrayList();

        carregarEstruturaTabela();
        carregarOpcoesPesquisa();
    }

    private void carregarEstruturaTabela() {

        //Exibi texto na tabela caso ela esteja vazia
        tabela.setPlaceholder(new Label(""));

        colunaCodigo = new TableColumn<>(Coluna.Usuario.CODIGO.getColuna());
        colunaNomeCompleto = new TableColumn<>(Coluna.Usuario.NOME_COMPLETO.getColuna());
        colunaNomeSistema = new TableColumn<>(Coluna.Usuario.NOME_SISTEMA.getColuna());
        colunaCodigoGrupoUsuario = new TableColumn<>(Coluna.Usuario.CODIGO_GRUPOUSUARIO.getColuna());
        colunaDescricaoGrupoUsuario = new TableColumn<>(Coluna.Usuario.DESCRICAO_GRUPOUSUARIO.getColuna());
        colunaStatus = new TableColumn<>(Coluna.Usuario.BLOQUEADO.getColuna());

        colunaCodigo.setMinWidth(85);
        colunaCodigo.setMaxWidth(colunaCodigo.getMinWidth());
        colunaNomeSistema.setMinWidth(200);
        colunaNomeSistema.setMaxWidth(colunaNomeSistema.getMinWidth());
        colunaCodigoGrupoUsuario.setMinWidth(85);
        colunaCodigoGrupoUsuario.setMaxWidth(colunaCodigoGrupoUsuario.getMinWidth());
        colunaDescricaoGrupoUsuario.setMinWidth(150);
        colunaDescricaoGrupoUsuario.setMaxWidth(colunaDescricaoGrupoUsuario.getMinWidth());
        colunaStatus.setMinWidth(85);
        colunaStatus.setMaxWidth(colunaStatus.getMinWidth());

        tabela.getSelectionModel().setCellSelectionEnabled(true);
        tabela.setTableMenuButtonVisible(true);

        //AJUSTA NOME COMPLETO
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabela.getColumns().add(colunaCodigo);
        tabela.getColumns().add(colunaNomeCompleto);
        tabela.getColumns().add(colunaNomeSistema);
        tabela.getColumns().add(colunaCodigoGrupoUsuario);
        tabela.getColumns().add(colunaDescricaoGrupoUsuario);
        tabela.getColumns().add(colunaStatus);

        colunaCodigo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCodigo()));
        colunaNomeCompleto.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNomeCompleto()));
        colunaNomeSistema.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNomeSistema()));
        colunaCodigoGrupoUsuario.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMGrupoUsuario().getCodigo()));
        colunaDescricaoGrupoUsuario.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMGrupoUsuario().getDescricao()));
        colunaStatus.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBloqueado() ? "SIM" : "NAO"));

    }

    private void carregarConteudoTabela() {
        oListUsuario.clear();
        sUsuario.listarTudoAsinc().thenAccept(list -> Platform.runLater(()-> {
            lsUsuario = list;
            tabela.getSortOrder().clear();
            oListUsuario.addAll(lsUsuario);
            tabela.getItems().setAll(oListUsuario);
            tabela.requestFocus();
        }));
    }

    private void carregarOpcoesPesquisa() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        List<String> list = Coluna.Usuario.getColunas();
        oList.clear();

        oList.add(Coluna.GERAL);
        oList.addAll(list);

        cbbxPesquisar.setItems(oList);
        cbbxPesquisar.getSelectionModel().selectFirst();
        cbbxPesquisar.setVisibleRowCount(9);
    }

    private void carregarEstruturaTreeLiberacao() {
        treeLiberacao.setCellFactory(CheckBoxTreeCell.forTreeView());
        treeLiberacao.setShowRoot(false);
        treeLiberacao.setRoot(new MontarLiberacoes().getMenus(TREE_SELECIONADO));
    }

    /** Obtem o painel para ser usado internamente.
     * @return AnchorPane - painel para usar internamente */
    public AnchorPane getPainel() {
        construirPainel();
        btnSelecionar.setVisible(false);
        campoTitulo.setVisible(false);
        campoTitulo.setPrefHeight(0);
        cInicio.setTitulo(campoTitulo, "Lista De Usuários");
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
