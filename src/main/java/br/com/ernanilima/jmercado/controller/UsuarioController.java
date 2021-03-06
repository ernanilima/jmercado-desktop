package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.listener.FocusListener;
import br.com.ernanilima.jmercado.controller.listener.KeyListener;
import br.com.ernanilima.jmercado.controller.popup.CoresPopUpConfirmacao;
import br.com.ernanilima.jmercado.controller.popup.PopUpBuscaController;
import br.com.ernanilima.jmercado.controller.popup.PopUpConfirmacaoController;
import br.com.ernanilima.jmercado.controller.popup.PopUpMensagemController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.MontarLiberacoes;
import br.com.ernanilima.jmercado.liberacao.TipoLiberacao;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import br.com.ernanilima.jmercado.model.Usuario;
import br.com.ernanilima.jmercado.service.GrupoUsuarioService;
import br.com.ernanilima.jmercado.service.UsuarioService;
import br.com.ernanilima.jmercado.service.componente.Legenda;
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
public class UsuarioController implements Initializable, ICadastro {

    @Autowired private ApplicationContext springContext;
    @Autowired private InicioController cInicio;
    @Autowired private GrupoUsuarioController cGrupoUsuario;
    @Autowired private PopUpConfirmacaoController ppConfirmacao;
    @Autowired private PopUpMensagemController ppMensagem;
    @Autowired private PopUpBuscaController ppBusca;
    @Autowired private FocusListener lFocus;
    @Autowired private KeyListener lKey;
    @Autowired private UsuarioService sUsuario;
    @Autowired private GrupoUsuarioService sGrupoUsuario;
    @Autowired private Utils utils;
    @Autowired private ValidarLiberacao vLiberacao;
    @Autowired private ValidarCodigo vCodigo;
    @Autowired private ValidarCampo vCampo;
    @Autowired private Legenda legenda;

    @Autowired private Pesquisa pesquisa;

    @Value("classpath:/fxml/cad_usuario.fxml")
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
    @FXML private TableView<Usuario> tabela;
    @FXML private Tab tpCadastrar;
    @FXML private AnchorPane box;
    @FXML private Label textoCampoCodigo1;
    @FXML private TextField campoCodigo;
    @FXML private Button btnAjudaUsuario;
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

    private final String TEXTO_LISTAR = "Lista De Usu??rios";
    private final String TEXTO_CADASTRAR = "Cadastrar Usu??rio";
    private final String TEXTO_EDITAR = "Editar Usu??rio";

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
    private String SENHA_USUARIO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
        oListUsuario = FXCollections.observableArrayList();

        //ACOES EM BOTOES
        btnPesquisar.setOnAction(e -> pesquisar());
        btnCadastrar.setOnAction(e -> cadastrar());
        btnEditar.setOnAction(e -> editar());
        btnExcluir.setOnAction(e -> excluir());
        btnAjudaUsuario.setOnAction(e -> ajudaUsuario());
        btnRemoverSenha.setOnAction(e -> removerSenha());
        btnIgualar.setOnAction(e -> igualarUsuario());
        btnGravar.setOnAction(e -> gravar());
        btnCancelar.setOnAction(e -> cancelar());
        btnBuscar.setOnAction(e -> buscar());

        // ACAO EM BOTAO RADIO
        grupoTipoLiberacao.selectedToggleProperty().addListener((ob, to, t1) -> tipoLiberacao());

        // ACOES DE FOCO
        campoPesquisar.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.PESQUISA));
        tabela.getFocusModel().focusedCellProperty().addListener(lFocus.tabelaActionListener(tabela, Coluna.Usuario.getColunasLegendas()));
        campoCodigo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.CODIGO));
        campoNomeCompleto.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.NOME_COMPLETO));
        campoNomeSistema.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.NOME_SISTEMA));
        campoCodGrupoUsuario.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.GrupoUsuario.CODIGO));
        campoDescricaoGrupoUsuario.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.GrupoUsuario.DESCRICAO));
        treeLiberacao.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.LIBERACAO_GRUPUSUA));

        // ACOES AO PRESSIONAR TECLAS
        campoPesquisar.setOnKeyPressed(lKey.campoPesquisaKeyPressed(tabela, this));
        painel.setOnKeyReleased(lKey.atalhoKeyReleased(this));

        // MASCARAS EM CAMPOS
        Mascara.textoNumeroMaiusculo(campoPesquisar, 50);
        Mascara.numeroInteiro(campoCodigo, 3);
        Mascara.textoNumeroMaiusculo(campoNomeCompleto, 50);
        Mascara.textoNumeroMaiusculo(campoNomeSistema, 10);
        Mascara.numeroInteiro(campoCodGrupoUsuario, 3);

        // EXIBE A ABA PRINCIPAL E DESABILITA AS OUTRAS
        utils.exibirAba(tab, tpListar, tpCadastrar);

        carregarEstruturaTabela();
        carregarOpcoesPesquisa();
    }

    /** Liberacoes solicitadas deve ser executada sempre que o controller for exibido */
    private void liberacoesSolicitadas() {
        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCadastrar, Liberacoes.CADASTROS_USUARIOS_USUARIOS_CADASTRAR);
        vLiberacao.liberacaoUsuario(btnEditar, Liberacoes.CADASTROS_USUARIOS_USUARIOS_EDITAR);
        vLiberacao.liberacaoUsuario(btnExcluir, Liberacoes.CADASTROS_USUARIOS_USUARIOS_EXCLUIR);
        vLiberacao.liberacaoUsuario(btnIgualar, Liberacoes.CADASTROS_USUARIOS_USUARIOS_IGUALAR);
        // IMPORTANTE: pendente criar forma de validar remover senha atual e de outros
        vLiberacao.liberacaoUsuario(btnRemoverSenha, Liberacoes.CADASTROS_USUARIOS_USUARIOS_REMOVERSENHA);
        vLiberacao.liberacaoUsuario(chbxBloqueado, Liberacoes.CADASTROS_USUARIOS_USUARIOS_BLOQUSUARIO);
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

    @Override
    public void pesquisar() {
        // atualiza a taleba com todos os itens ja carregados
        // antes de realizar pesquisa
        // OBS: nao realiza nova consulta no banco
        tabela.getItems().setAll(oListUsuario);

        // realiza pesquisa
        pesquisa.pesquisaUsuario(cbbxPesquisar, tabela, campoPesquisar);
    }

    @Override
    public void cadastrar() {
        TREE_SELECIONADO = new HashSet<>();
        carregarEstruturaTreeLiberacao();
        grupoTipoLiberacao.selectToggle(btnRLiberacaoUsuario);
        campoCodigo.setDisable(true);
        btnRemoverSenha.setDisable(true);
        SENHA_USUARIO = null;
        cInicio.setTitulo(campoTitulo, TEXTO_CADASTRAR);
        utils.exibirAba(tab, tpCadastrar, tpListar);
    }

    @Override
    public void editar() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            Usuario mUsuario = tabela.getItems().get(linhaSelecionada);
            SENHA_USUARIO = mUsuario.getSenha();
            campoCodigo.setDisable(true);
            campoCodigo.setText(String.valueOf(mUsuario.getCodigo()));
            chbxBloqueado.setSelected(mUsuario.getBloqueado());
            campoNomeCompleto.setText(mUsuario.getNomeCompleto());
            campoNomeSistema.setText(mUsuario.getNomeSistema());
            campoCodGrupoUsuario.setText(String.valueOf(mUsuario.getMGrupoUsuario().getCodigo()));
            campoDescricaoGrupoUsuario.setText(mUsuario.getMGrupoUsuario().getDescricao());
            TREE_SELECIONADO = new HashSet<>();
            if (mUsuario.getTipoLiberacao() == TipoLiberacao.USUARIO) {
                grupoTipoLiberacao.selectToggle(btnRLiberacaoUsuario);
                // busca as liberacoes do usuario e atualiza a tabletree
                mUsuario.getLiberacoes().stream().map(CheckBoxTreeItem::new).forEach(l -> TREE_SELECIONADO.add(l));
            } else if (mUsuario.getTipoLiberacao() == TipoLiberacao.GRUPO) {
                grupoTipoLiberacao.selectToggle(btnRLiberacaoGrupo);
                // busca as liberacoes do grupo de usuario e atualiza a tabletree
                mUsuario.getMGrupoUsuario().getLiberacoes().stream().map(CheckBoxTreeItem::new).forEach(l -> TREE_SELECIONADO.add(l));
            }
            carregarEstruturaTreeLiberacao();
            cInicio.setTitulo(campoTitulo, TEXTO_EDITAR);
            utils.exibirAba(tab, tpCadastrar, tpListar);
        }
    }

    @Override
    public void excluir() {
        int linhaSelecionada = tabela.getSelectionModel().getFocusedIndex();
        if (linhaSelecionada != -1) {
            ppConfirmacao.exibirPopUp(CoresPopUpConfirmacao.VERMELHO_VERDE,
                    MensagemAlerta.excluir(tabela.getItems().get(linhaSelecionada).getNomeSistema()));
            if (ppConfirmacao.getRsultado()) {
                sUsuario.remover(tabela.getItems().get(linhaSelecionada));
                carregarConteudoTabela();
                tabela.getSelectionModel().select(linhaSelecionada > 0 ? linhaSelecionada - 1 : 0);
            }
        }
    }

    private void removerSenha() {
        ppConfirmacao.exibirPopUp(CoresPopUpConfirmacao.VERMELHO_VERDE, MensagemAlerta.excluir("SENHA"));
        if (ppConfirmacao.getRsultado()) {
            Usuario mUsuario = tabela.getItems().get(tabela.getSelectionModel().getFocusedIndex());
            SENHA_USUARIO = null;
            mUsuario.setSenha(null);
            sUsuario.gravar(mUsuario);
        }
    }

    private void igualarUsuario() {
        ppBusca.exibirPopUp("C??DIGO DO USU??RIO QUE DESEJA OBTER AS PERMISS??ES:");
        if (ppBusca.getRsultado() != null) {
            Usuario mUsuario = sUsuario.getPorId(Filtro.pInt(ppBusca.getRsultado()));
            if (mUsuario == null) {
                legenda.exibirAlerta(MensagemAlerta.naoLocalizado("USU??RIO"));

            } else {
                campoCodGrupoUsuario.setText(String.valueOf(mUsuario.getMGrupoUsuario().getCodigo()));
                campoDescricaoGrupoUsuario.setText(mUsuario.getMGrupoUsuario().getDescricao());

                TREE_SELECIONADO = new HashSet<>();
                if (mUsuario.getTipoLiberacao() == TipoLiberacao.USUARIO) {
                    grupoTipoLiberacao.selectToggle(btnRLiberacaoUsuario);
                    mUsuario.getLiberacoes().stream().map(CheckBoxTreeItem::new).forEach(l -> TREE_SELECIONADO.add(l));
                } else if (mUsuario.getTipoLiberacao() == TipoLiberacao.GRUPO) {
                    grupoTipoLiberacao.selectToggle(btnRLiberacaoGrupo);
                    mUsuario.getMGrupoUsuario().getLiberacoes().stream().map(CheckBoxTreeItem::new).forEach(l -> TREE_SELECIONADO.add(l));
                }
                carregarEstruturaTreeLiberacao();
            }
        }
    }

    @Override
    public void gravar() {
        if (validarCampos()) {
            Usuario mUsuario = new Usuario(
                    Filtro.pInt(campoCodigo.getText()),
                    campoNomeCompleto.getText(),
                    campoNomeSistema.getText(),
                    chbxBloqueado.isSelected(),
                    sGrupoUsuario.getPorId(Filtro.pInt(campoCodGrupoUsuario.getText()))
            );

            if (grupoTipoLiberacao.getSelectedToggle() == btnRLiberacaoUsuario) {
                mUsuario.setTipoLiberacao(TipoLiberacao.USUARIO);
                TREE_SELECIONADO.stream().map(TreeItem::getValue).map(Collections::singletonList).forEach(mUsuario::setLiberacoes);
            } else if (grupoTipoLiberacao.getSelectedToggle() == btnRLiberacaoGrupo) {
                mUsuario.setTipoLiberacao(TipoLiberacao.GRUPO);
            }

            mUsuario.setSenha(SENHA_USUARIO);

            sUsuario.gravar(mUsuario);
            limpar();
            carregarConteudoTabela();
            cInicio.setTitulo(campoTitulo, TEXTO_LISTAR);
            utils.exibirAba(tab, tpListar, tpCadastrar);
        }
    }

    @Override
    public void cancelar() {
        limpar();
        cInicio.setTitulo(campoTitulo, TEXTO_LISTAR);
        utils.exibirAba(tab, tpListar, tpCadastrar);
    }

    /** Busca grupo de usuario para associar ao cadastro de usuario */
    private void buscar() {
        cGrupoUsuario.exibirModal();
        campoCodGrupoUsuario.requestFocus();
        if (cGrupoUsuario.getGrupoUsuario() != null) {
            campoCodGrupoUsuario.setText(String.valueOf(cGrupoUsuario.getGrupoUsuario().getCodigo()));
            campoDescricaoGrupoUsuario.setText(cGrupoUsuario.getGrupoUsuario().getDescricao());
        }
    }

    /** Exibe popup de ajuda na criacao de usuario novo
     * Opcao poderia ser apenas para cadastrar novo usuario, mas optei
     * por exibir esta opcao para cadastrar e editar usuario */
    private void ajudaUsuario() {
        ppMensagem.exibirPopUp("IMPORTANTE: O CADASTRO DE UM USU??RIO NOVO ?? GERADO SEM SENHA.\n" +
                "PARA QUE O USU??RIO OBTENHA UMA SENHA, DEVE-SE UTILIZAR\n" +
                "A OP????O DE MUDAR A SENHA DISPON??VEL NA TELA DE LOGIN.\n\n" +
                "A OP????O DE MUDAR A SENHA N??O EXIBE NENHUMA INFORMA????O DE\n" +
                "QUE O USU??RIO EXISTE, ISSO EVITA QUE SEJA ALTERADO A\n" +
                "SENHA DE UM USU??RIO QUALQUER.");
    }

    private void limpar() {
        btnRemoverSenha.setDisable(false);
        utils.limparCampos(campoPesquisar, campoCodigo, campoNomeCompleto,
                campoNomeSistema, campoCodGrupoUsuario, campoDescricaoGrupoUsuario);
    }

    private boolean validarCampos() {
        // campo de codigo eh gerado automaticamente
        // por esse motivo nao precisa de validacao
        return vCampo.campoVazio(campoNomeCompleto, textoCampoNomeCompleto) &&
                vCampo.campoVazio(campoNomeSistema, textoCampoNomeSistema) &&
                vCampo.campoVazio(campoCodGrupoUsuario, textoCampoGrupoUsuario) &&
                vCodigo.buscarExistente(campoCodGrupoUsuario, campoDescricaoGrupoUsuario, sGrupoUsuario, textoCampoGrupoUsuario);
    }

    /** Oculta as permicoes caso o tipo de liberacao seja por grupo de usuario */
    private void tipoLiberacao() {
        if (grupoTipoLiberacao.getSelectedToggle() == btnRLiberacaoUsuario)
            treeLiberacao.setDisable(false);
        else if (grupoTipoLiberacao.getSelectedToggle() == btnRLiberacaoGrupo)
            treeLiberacao.setDisable(true);
    }

    /** Obtem o painel para ser usado internamente.
     * @return AnchorPane - painel para usar internamente */
    public AnchorPane getPainel() {
        construirPainel();
        btnSelecionar.setVisible(false);
        campoTitulo.setVisible(false);
        campoTitulo.setPrefHeight(0);
        cInicio.setTitulo(campoTitulo, TEXTO_LISTAR);
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
            liberacoesSolicitadas();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
