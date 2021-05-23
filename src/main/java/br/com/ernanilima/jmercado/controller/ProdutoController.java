package br.com.ernanilima.jmercado.controller;

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
import java.util.ResourceBundle;

@Controller
public class ProdutoController implements Initializable {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;

    @Value("classpath:/fxml/cad_produto.fxml")
    private Resource R_FXML;
    @Value("classpath:/css/modal.css")
    private Resource R_CSS;

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
    @FXML private TableView<?> tabela;
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

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
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
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
