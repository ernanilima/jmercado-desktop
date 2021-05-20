package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.listener.FocusListener;
import br.com.ernanilima.jmercado.controller.listener.KeyListener;
import br.com.ernanilima.jmercado.controller.popup.CoresPopUpConfirmacao;
import br.com.ernanilima.jmercado.controller.popup.PopUpBuscaController;
import br.com.ernanilima.jmercado.controller.popup.PopUpConfirmacaoController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.MontarLiberacoes;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import br.com.ernanilima.jmercado.model.GrupoUsuario;
import br.com.ernanilima.jmercado.service.GrupoUsuarioService;
import br.com.ernanilima.jmercado.service.componente.Legenda;
import br.com.ernanilima.jmercado.service.componente.Mascara;
import br.com.ernanilima.jmercado.service.componente.Pesquisa;
import br.com.ernanilima.jmercado.service.constante.Mensagem;
import br.com.ernanilima.jmercado.service.constante.MensagemAlerta;
import br.com.ernanilima.jmercado.service.constante.enums.Coluna;
import br.com.ernanilima.jmercado.service.validacao.ValidarCampo;
import br.com.ernanilima.jmercado.utils.Filtro;
import br.com.ernanilima.jmercado.utils.Utils;
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
import java.util.*;

@Controller
public class GrupoUsuarioController implements Initializable, ICadastro {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;
    @Autowired private PopUpConfirmacaoController ppConfirmacao;
    @Autowired private PopUpBuscaController ppBusca;
    @Autowired private FocusListener lFocus;
    @Autowired private KeyListener lKey;
    @Autowired private GrupoUsuarioService sGrupoUsuario;
    @Autowired private Utils utils;
    @Autowired private ValidarLiberacao vLiberacao;
    @Autowired private ValidarCampo vCampo;
    @Autowired private Legenda legenda;

    @Autowired private Pesquisa pesquisa;

    @Value("classpath:/fxml/cad_grupousuario.fxml")
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
    @FXML private TreeView<Liberacoes> treeLiberacao;

    private ObservableList<GrupoUsuario> oListGrupoUsuario;
    private List<GrupoUsuario> lsGrupoUsuario;
    private TableColumn<GrupoUsuario, Integer> colunaCodigo;
    private TableColumn<GrupoUsuario, String> colunaDescricao;

    private Set<TreeItem<Liberacoes>> TREE_SELECIONADO;
    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;
    private Scene SCENE;
    private boolean LINHA_SELECIONADA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
        oListGrupoUsuario = FXCollections.observableArrayList();
        TREE_SELECIONADO = new HashSet<>();
        LINHA_SELECIONADA = false;

        // ACOES EM BOTOES
        btnSelecionar.setOnAction(e -> selecionar());
        btnCadastrar.setOnAction(e -> cadastrar());
        btnEditar.setOnAction(e -> editar());
        btnExcluir.setOnAction(e -> excluir());
        btnPesquisar.setOnAction(e -> pesquisar());
        btnIgualar.setOnAction(e -> igualarGrupo());
        btnGravar.setOnAction(e -> gravar());
        btnCancelar.setOnAction(e -> cancelar());

        // ACOES DE FOCO
        campoPesquisar.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.PESQUISA));
        tabela.getFocusModel().focusedCellProperty().addListener(lFocus.tabelaActionListener(tabela, Coluna.GrupoUsuario.getColunasLegendas()));
        campoCodigo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.GrupoUsuario.CODIGO));
        campoDescricao.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.GrupoUsuario.DESCRICAO));
        treeLiberacao.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.LIBERACAO_GRUPUSUA));

        // ACOES AO PRESSIONAR TECLAS
        campoPesquisar.setOnKeyPressed(lKey.campoPesquisaKeyPressed(tabela, this));
        painel.setOnKeyReleased(lKey.atalhoKeyReleased(this));

        // MASCARAS EM CAMPOS
        Mascara.textoNumeroMaiusculo(campoPesquisar, 50);
        Mascara.numeroInteiro(campoCodigo, 3);
        Mascara.textoNumeroMaiusculo(campoDescricao, 50);

        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCadastrar, Liberacoes.CADASTROS_USUARIOS_GRUPOS_USUARIOS_CADASTRAR);
        vLiberacao.liberacaoUsuario(btnEditar, Liberacoes.CADASTROS_USUARIOS_GRUPOS_USUARIOS_EDITAR);
        vLiberacao.liberacaoUsuario(btnExcluir, Liberacoes.CADASTROS_USUARIOS_GRUPOS_USUARIOS_EXCLUIR);
        vLiberacao.liberacaoUsuario(btnIgualar, Liberacoes.CADASTROS_USUARIOS_GRUPOS_USUARIOS_IGUALAR);

        // EXIBE A ABA PRINCIPAL E DESABILITA AS OUTRAS
        utils.exibirAba(tab, tpListar, tpCadastrar);

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
        oListGrupoUsuario.clear();
        sGrupoUsuario.listarTudoAsinc().thenAccept(list -> Platform.runLater(()-> {
            lsGrupoUsuario = list;
            tabela.getSortOrder().clear();
            oListGrupoUsuario.addAll(lsGrupoUsuario);
            tabela.getItems().setAll(oListGrupoUsuario);
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

    private void carregarEstruturaTreeLiberacao() {
        treeLiberacao.setCellFactory(CheckBoxTreeCell.forTreeView());
        treeLiberacao.setShowRoot(false);
        treeLiberacao.setRoot(new MontarLiberacoes().getMenus(TREE_SELECIONADO));
    }

    @Override
    public void pesquisar() {
        // atualiza a taleba com todos os itens ja carregados
        // antes de realizar pesquisa
        // OBS: nao realiza nova consulta no banco
        tabela.getItems().setAll(oListGrupoUsuario);

        // realiza pesquisa
        pesquisa.pesquisaGrupoUsuario(cbbxPesquisar, tabela, campoPesquisar);
    }

    public void selecionar() {
        if (btnSelecionar.isVisible() && tabela.getSelectionModel().getFocusedIndex() != -1) {
            LINHA_SELECIONADA = true;
            STAGE.close();
        }
    }

    @Override
    public void cadastrar() {
        TREE_SELECIONADO = new HashSet<>();
        carregarEstruturaTreeLiberacao();
        campoCodigo.setDisable(true);
        cInicio.setTitulo(campoTitulo, "Cadastrar Grupo De Usuário");
        utils.exibirAba(tab, tpCadastrar, tpListar);
    }

    @Override
    public void editar() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            GrupoUsuario mGrupoUsuario = tabela.getItems().get(linhaSelecionada);
            campoCodigo.setText(String.valueOf(mGrupoUsuario.getCodigo()));
            campoDescricao.setText(mGrupoUsuario.getDescricao());

            TREE_SELECIONADO = new HashSet<>();
            for (Liberacoes liberacao : mGrupoUsuario.getLiberacoes()) {
                CheckBoxTreeItem<Liberacoes> checkBoxTreeItem = new CheckBoxTreeItem<>(liberacao);
                TREE_SELECIONADO.add(checkBoxTreeItem);
            }
            carregarEstruturaTreeLiberacao();

            campoCodigo.setDisable(true);
            cInicio.setTitulo(campoTitulo, "Editar Grupo De Usuário");
            utils.exibirAba(tab, tpCadastrar, tpListar);
        }
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            ppConfirmacao.exibirPopUp(CoresPopUpConfirmacao.VERMELHO_VERDE,
                    MensagemAlerta.excluir(tabela.getItems().get(linhaSelecionada).getDescricao()));
            if (ppConfirmacao.getRsultado()) {
                sGrupoUsuario.remover(tabela.getItems().get(linhaSelecionada));
                carregarConteudoTabela();
                tabela.getSelectionModel().select(linhaSelecionada > 0 ? linhaSelecionada - 1 : 0);
            }
        }
    }

    /** Permite que o grupo atual tenha suas permissoes igualadas com outro grupo */
    private void igualarGrupo() {
        ppBusca.exibirPopUp("CÓDIGO DO GRUPO QUE DESEJA OBTER AS PERMISSÕES.");
        if (ppBusca.getRsultado() != null) {
            GrupoUsuario mGrupoUsuario = sGrupoUsuario.getPorId(Filtro.pInt(ppBusca.getRsultado()));
            if (mGrupoUsuario == null) {
                legenda.exibirAlerta(MensagemAlerta.naoLocalizado("GRUPO DE USUÁRIO"));

            } else {
                TREE_SELECIONADO = new HashSet<>();
                mGrupoUsuario.getLiberacoes().stream().map(CheckBoxTreeItem::new).forEach(l -> TREE_SELECIONADO.add(l));
                carregarEstruturaTreeLiberacao();
            }
        }
    }

    @Override
    public void gravar() {
        if (validarCampos()) {
            GrupoUsuario mGrupoUsuario = new GrupoUsuario(
                    Filtro.pInt(campoCodigo.getText()),
                    campoDescricao.getText()
            );

            TREE_SELECIONADO.stream().map(TreeItem::getValue).map(Collections::singletonList).forEach(mGrupoUsuario::setLiberacoes);

            sGrupoUsuario.gravar(mGrupoUsuario);
            limpar();
            carregarConteudoTabela();
            cInicio.setTitulo(campoTitulo, "Lista De Grupos de Usuários");
            utils.exibirAba(tab, tpListar, tpCadastrar);
        }
    }

    @Override
    public void cancelar() {
        limpar();
        cInicio.setTitulo(campoTitulo, "Lista De Grupos de Usuários");
        utils.exibirAba(tab, tpListar, tpCadastrar);
    }

    private void limpar() {
        utils.limparCampos(campoPesquisar, campoCodigo, campoDescricao);
    }

    private boolean validarCampos() {
        // campo de codigo eh gerado automaticamente
        // por esse motivo nao precisa de validacao
        return vCampo.campoVazio(campoDescricao, textoCampoDescricao);
    }

    /** Obtem o grupo de usuario selecionado
     * @return GrupoUsuario */
    public GrupoUsuario getGrupoUsuario() {
        GrupoUsuario mGrupoUsuario = null;
        if (LINHA_SELECIONADA) {
            int linha = tabela.getSelectionModel().getFocusedIndex();
            mGrupoUsuario = tabela.getItems().get(linha);
        }
        return mGrupoUsuario;
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

    /** Exibe o painel em forma de dialog */
    public void exibirModal() {
        STAGE = null;
        construirPainel();

        LINHA_SELECIONADA = false;
        STAGE.setResizable(false);
        if (ROOT.getScene() == null) {
            SCENE = new Scene(ROOT);
        }

        STAGE.setScene(SCENE);
        btnSelecionar.setVisible(true);
        campoTitulo.setVisible(true);
        campoTitulo.setPrefHeight(campoTitulo.getMaxHeight());
        cInicio.setTitulo(campoTitulo, "Lista De Grupos De Usuários");
        STAGE.showAndWait();
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
