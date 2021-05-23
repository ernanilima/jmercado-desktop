package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.listener.FocusListener;
import br.com.ernanilima.jmercado.controller.listener.KeyListener;
import br.com.ernanilima.jmercado.model.Produto;
import br.com.ernanilima.jmercado.service.ProdutoService;
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
public class ProdutoController implements Initializable, ICadastro {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;
    @Autowired private FocusListener lFocus;
    @Autowired private KeyListener lKey;
    @Autowired private ProdutoService sProduto;
    @Autowired private Utils utils;

    @Value("classpath:/fxml/cad_produto.fxml")
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
    @FXML private TableView<Produto> tabela;
    @FXML private Tab tpCadastrar;
    @FXML private AnchorPane box;
    @FXML private Label textoCampoCodigo;
    @FXML private TextField campoCodigo;
    @FXML private Label textoCampoCodigoBarras;
    @FXML private TextField campoCodigoBarras;
    @FXML private Button btnGravar;
    @FXML private Button btnCancelar;
    @FXML private Label textoCampoDescricaoProduto;
    @FXML private TextField campoDescricaoProduto;
    @FXML private Label textoCampoDescricaoCupom;
    @FXML private TextField campoDescricaoCupom;
    @FXML private Label textoCampoDescricaoCliente;
    @FXML private TextField campoDescricaoCliente;
    @FXML private Label textoCampoComplemento;
    @FXML private TextField campoComplemento;
    @FXML private Label textoCampoDepartamento;
    @FXML private TextField campoCodigoDepartamento;
    @FXML private Button btnBuscarDepartamento;
    @FXML private TextField campoDescricaoDepartamento;
    @FXML private Label textoCampoGrupo;
    @FXML private TextField campoCodigoGrupo;
    @FXML private Button btnBuscarGrupo;
    @FXML private TextField campoDescricaoGrupo;
    @FXML private Label textoCampoSubgrupo;
    @FXML private TextField campoCodigoSubgrupo;
    @FXML private Button btnBuscarSubgrupo;
    @FXML private TextField campoDescricaoSubgrupo;
    @FXML private Label textoCampoPrecoVenda;
    @FXML private TextField campoPrecoVenda;

    private ObservableList<Produto> oListProduto;
    private List<Produto> lsProduto;
    private TableColumn<Produto, Integer> colunaCodigo;
    private TableColumn<Produto, String> colunaDescricao;
    private TableColumn<Produto, Long> colunaCodigiBarras;
    private TableColumn<Produto, Integer> colunaCodigoSubgrupo;
    private TableColumn<Produto, String> colunaDescricaoSubgrupo;
    private TableColumn<Produto, String> colunaPrecoVenda;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
        oListProduto = FXCollections.observableArrayList();

        //ACOES EM BOTOES
        btnPesquisar.setOnAction(e -> pesquisar());
        btnCadastrar.setOnAction(e -> cadastrar());
        btnEditar.setOnAction(e -> editar());
        btnExcluir.setOnAction(e -> excluir());
        btnGravar.setOnAction(e -> gravar());
        btnCancelar.setOnAction(e -> cancelar());
        btnBuscarDepartamento.setOnAction(e -> buscarDepartamento());
        btnBuscarGrupo.setOnAction(e -> buscarGrupo());
        btnBuscarSubgrupo.setOnAction(e -> buscarSubgrupo());

        // ACOES DE FOCO
        campoPesquisar.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.PESQUISA));
        tabela.getFocusModel().focusedCellProperty().addListener(lFocus.tabelaActionListener(tabela, Coluna.Produto.getColunasLegendas()));
        campoCodigo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Produto.CODIGO));
        campoCodigoBarras.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Produto.CODIGO_BARRAS));
        campoDescricaoProduto.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Produto.DESCRICAO_PRODUTO));
        campoDescricaoCupom.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Produto.DESCRICAO_CUPOM));
        campoDescricaoCliente.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Produto.DESCRICAO_CLIENTE));
        campoComplemento.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Produto.COMPLEMENTO));
        campoCodigoDepartamento.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdDepartamento.CODIGO));
        campoDescricaoDepartamento.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdDepartamento.DESCRICAO));
        campoCodigoGrupo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdGrupo.CODIGO));
        campoDescricaoGrupo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdGrupo.DESCRICAO));
        campoCodigoSubgrupo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdSubgrupo.CODIGO));
        campoDescricaoSubgrupo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.ProdSubgrupo.DESCRICAO));
        campoPrecoVenda.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Produto.PRECO_VENDA));

        // ACOES AO PRESSIONAR TECLAS
        campoPesquisar.setOnKeyPressed(lKey.campoPesquisaKeyPressed(tabela, this));
        painel.setOnKeyReleased(lKey.atalhoKeyReleased(this));

        // MASCARAS EM CAMPOS
        Mascara.textoNumeroMaiusculo(campoPesquisar, 50);
        Mascara.numeroInteiro(campoCodigo, 3);
        Mascara.numeroInteiro(campoCodigoBarras, 14);
        Mascara.textoNumeroMaiusculo(campoDescricaoProduto, 50);
        Mascara.textoNumeroMaiusculo(campoDescricaoCupom, 50);
        Mascara.textoNumeroMaiusculo(campoDescricaoCliente, 50);
        Mascara.textoNumeroMaiusculo(campoComplemento, 50);
        Mascara.numeroInteiro(campoCodigoDepartamento, 3);
        Mascara.numeroInteiro(campoCodigoGrupo, 3);
        Mascara.numeroInteiro(campoCodigoSubgrupo, 3);
        Mascara.numeroMonetario(campoPrecoVenda, 2);

        // EXIBE A ABA PRINCIPAL E DESABILITA AS OUTRAS
        utils.exibirAba(tab, tpListar, tpCadastrar);

        carregarEstruturaTabela();
        carregarOpcoesPesquisa();
    }

    private void carregarEstruturaTabela() {
        //Exibi texto na tabela caso ela esteja vazia
        tabela.setPlaceholder(new Label(""));

        colunaCodigo = new TableColumn<>(Coluna.Produto.CODIGO.getColuna());
        colunaDescricao = new TableColumn<>(Coluna.Produto.DESCRICAO_PRODUTO.getColuna());
        colunaCodigiBarras = new TableColumn<>(Coluna.Produto.CODIGO_BARRAS.getColuna());
        colunaCodigoSubgrupo = new TableColumn<>(Coluna.Produto.CODIGO_SUBGRUPO.getColuna());
        colunaDescricaoSubgrupo = new TableColumn<>(Coluna.Produto.DESCRICAO_SUBGRUPO.getColuna());
        colunaPrecoVenda = new TableColumn<>(Coluna.Produto.PRECO_DE_VENDA.getColuna());

        colunaCodigo.setMinWidth(85);
        colunaCodigo.setMaxWidth(colunaCodigo.getMinWidth());
        colunaCodigiBarras.setMinWidth(120);
        colunaCodigiBarras.setMaxWidth(colunaCodigiBarras.getMinWidth());
        colunaCodigoSubgrupo.setMinWidth(120);
        colunaCodigoSubgrupo.setMaxWidth(colunaCodigoSubgrupo.getMinWidth());
        colunaDescricaoSubgrupo.setMinWidth(200);
        colunaDescricaoSubgrupo.setMaxWidth(colunaDescricaoSubgrupo.getMinWidth());
        colunaPrecoVenda.setMinWidth(150);
        colunaPrecoVenda.setMaxWidth(colunaPrecoVenda.getMinWidth());

        tabela.getSelectionModel().setCellSelectionEnabled(true);
        tabela.setTableMenuButtonVisible(true);

        //AJUSTA DESCRICAO
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabela.getColumns().add(colunaCodigo);
        tabela.getColumns().add(colunaDescricao);
        tabela.getColumns().add(colunaCodigiBarras);
        tabela.getColumns().add(colunaCodigoSubgrupo);
        tabela.getColumns().add(colunaDescricaoSubgrupo);
        tabela.getColumns().add(colunaPrecoVenda);

        colunaCodigo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCodigo()));
        colunaDescricao.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescricao()));
        colunaCodigiBarras.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCodigoBarras()));
        colunaCodigoSubgrupo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMSubgrupo().getCodigo()));
        colunaDescricaoSubgrupo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMSubgrupo().getDescricao()));
        colunaPrecoVenda.setCellValueFactory(cellData -> new SimpleObjectProperty<>(String.valueOf(cellData.getValue().getMPreco().getPrecoVenda())));
    }

    private void carregarConteudoTabela() {
        oListProduto.clear();
        sProduto.listarTudoAsinc().thenAccept(list -> Platform.runLater(()-> {
            lsProduto = list;
            tabela.getSortOrder().clear();
            oListProduto.addAll(lsProduto);
            tabela.getItems().setAll(oListProduto);
            tabela.requestFocus();
        }));
    }

    /** Carrega as opcoes para pesquisa no combobox */
    private void carregarOpcoesPesquisa() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        List<String> list = Coluna.Produto.getColunas();
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

    private void buscarDepartamento() {

    }

    private void buscarGrupo() {

    }

    private void buscarSubgrupo() {

    }

    /** Obtem o painel para ser usado internamente.
     * @return AnchorPane - painel para usar internamente */
    public AnchorPane getPainel() {
        construirPainel();
        btnSelecionar.setVisible(false);
        campoTitulo.setVisible(false);
        campoTitulo.setPrefHeight(0);
        cInicio.setTitulo(campoTitulo, "Lista De Produtos");
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
