package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.listener.FocusListener;
import br.com.ernanilima.jmercado.controller.listener.KeyListener;
import br.com.ernanilima.jmercado.controller.popup.CoresPopUpConfirmacao;
import br.com.ernanilima.jmercado.controller.popup.PopUpConfirmacaoController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import br.com.ernanilima.jmercado.model.Grupo;
import br.com.ernanilima.jmercado.service.DepartamentoService;
import br.com.ernanilima.jmercado.service.GrupoService;
import br.com.ernanilima.jmercado.service.componente.Mascara;
import br.com.ernanilima.jmercado.service.componente.Pesquisa;
import br.com.ernanilima.jmercado.service.constante.Mensagem;
import br.com.ernanilima.jmercado.service.constante.MensagemAlerta;
import br.com.ernanilima.jmercado.service.constante.enums.Coluna;
import br.com.ernanilima.jmercado.service.validacao.ValidarCampo;
import br.com.ernanilima.jmercado.service.validacao.ValidarCodigo;
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
    @Autowired private DepartamentoController cDepartamento;
    @Autowired private PopUpConfirmacaoController ppConfirmacao;
    @Autowired private FocusListener lFocus;
    @Autowired private KeyListener lKey;
    @Autowired private GrupoService sGrupo;
    @Autowired private DepartamentoService sDepartamento;
    @Autowired private Utils utils;
    @Autowired private ValidarLiberacao vLiberacao;
    @Autowired private ValidarCodigo vCodigo;
    @Autowired private ValidarCampo vCampo;

    @Autowired private Pesquisa pesquisa;

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
    private Scene SCENE;
    private boolean LINHA_SELECIONADA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
        oListGrupo = FXCollections.observableArrayList();
        LINHA_SELECIONADA = false;

        //ACOES EM BOTOES
        btnPesquisar.setOnAction(e -> pesquisar());
        btnSelecionar.setOnAction(e -> selecionar());
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

    /** Liberacoes solicitadas deve ser executada sempre que o controller for exibido */
    private void liberacoesSolicitadas() {
        // VALIDACAO DE LIBERACOES DE USUARIO
        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCadastrar, Liberacoes.CADASTROS_PRODUTOS_GRUPO_CADASTRAR);
        vLiberacao.liberacaoUsuario(btnEditar, Liberacoes.CADASTROS_PRODUTOS_GRUPO_EDITAR);
        vLiberacao.liberacaoUsuario(btnExcluir, Liberacoes.CADASTROS_PRODUTOS_GRUPO_EXCLUIR);
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
        List<String> list = Coluna.ProdGrupo.getColunas();
        oList.clear();

        oList.add(Coluna.GERAL);
        oList.addAll(list);

        cbbxPesquisar.setItems(oList);
        cbbxPesquisar.getSelectionModel().selectFirst();
        cbbxPesquisar.setVisibleRowCount(9);
    }

    @Override
    public void pesquisar() {
        // atualiza a taleba com todos os itens ja carregados
        // antes de realizar pesquisa
        // OBS: nao realiza nova consulta no banco
        tabela.getItems().setAll(oListGrupo);

        // realiza pesquisa
        pesquisa.pesquisaGrupo(cbbxPesquisar, tabela, campoPesquisar);
    }

    public void selecionar() {
        if (btnSelecionar.isVisible() && tabela.getSelectionModel().getFocusedIndex() != -1) {
            LINHA_SELECIONADA = true;
            STAGE.close();
        }
    }

    @Override
    public void cadastrar() {
        cInicio.setTitulo(campoTitulo, "Cadastrar Grupo De Produto");
        utils.exibirAba(tab, tpCadastrar, tpListar);
        campoCodigo.requestFocus();
    }

    @Override
    public void editar() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            Grupo mGrupo = tabela.getItems().get(linhaSelecionada);
            campoCodigo.setDisable(true);
            campoCodigo.setText(String.valueOf(mGrupo.getCodigo()));
            campoDescricao.setText(mGrupo.getDescricao());
            campoCodDepartamento.setText(String.valueOf(mGrupo.getMDepartamento().getCodigo()));
            campoDescricaoDepartamento.setText(mGrupo.getMDepartamento().getDescricao());

            cInicio.setTitulo(campoTitulo, "Editar Grupo De Produtos");
            utils.exibirAba(tab, tpCadastrar, tpListar);
            campoDescricao.requestFocus();
        }
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            ppConfirmacao.exibirPopUp(CoresPopUpConfirmacao.VERMELHO_VERDE,
                    MensagemAlerta.excluir(tabela.getItems().get(linhaSelecionada).getDescricao()));
            if (ppConfirmacao.getRsultado()) {
                sGrupo.remover(tabela.getItems().get(linhaSelecionada));
                carregarConteudoTabela();
                tabela.getSelectionModel().select(linhaSelecionada > 0 ? linhaSelecionada - 1 : 0);
            }
        }
    }

    @Override
    public void gravar() {
        if (validarCampos()) {
            Grupo mGrupo = new Grupo(
                    Filtro.pInt(campoCodigo.getText()),
                    campoDescricao.getText(),
                    sDepartamento.getPorId(Filtro.pInt(campoCodDepartamento.getText()))
            );

            sGrupo.gravar(mGrupo);
            limpar();
            carregarConteudoTabela();
            cInicio.setTitulo(campoTitulo, "Lista De Grupos De Produtos");
            utils.exibirAba(tab, tpListar, tpCadastrar);
        }
    }

    @Override
    public void cancelar() {
        limpar();
        cInicio.setTitulo(campoTitulo, "Lista De Grupos De Produtos");
        utils.exibirAba(tab, tpListar, tpCadastrar);
    }

    /** Busca departamento para associar ao cadastro de grupo */
    private void buscar() {
        cDepartamento.exibirModal();
        campoCodDepartamento.requestFocus();
        if (cDepartamento.getDepartamento() != null) {
            campoCodDepartamento.setText(String.valueOf(cDepartamento.getDepartamento().getCodigo()));
            campoDescricaoDepartamento.setText(cDepartamento.getDepartamento().getDescricao());
        }
    }

    private void limpar() {
        utils.limparCampos(campoPesquisar, campoCodigo, campoDescricao,
                campoCodDepartamento, campoDescricaoDepartamento);
    }

    private boolean validarCampos() {
        return vCampo.campoVazio(campoCodigo, textoCampoCodigo) &&
                vCampo.campoVazio(campoDescricao, textoCampoDescricao) &&
                vCampo.campoVazio(campoCodDepartamento, textoCampoDepartamento) &&
                !vCodigo.novo(campoCodigo, sGrupo) &&
                vCodigo.buscarExistente(campoCodDepartamento, campoDescricaoDepartamento, sDepartamento, textoCampoDepartamento);
    }

    /** Obter o grupo selecionado
     * @return Grupo */
    public Grupo obterGrupo() {
        Grupo mGrupo = null;
        if (LINHA_SELECIONADA) {
            int linha = tabela.getSelectionModel().getFocusedIndex();
            mGrupo = tabela.getItems().get(linha);
        }
        return mGrupo;
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
        cInicio.setTitulo(campoTitulo, "Lista De Grupos De Produtos");
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
            liberacoesSolicitadas();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
