package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.model.Usuario;
import br.com.ernanilima.jmercado.service.UsuarioService;
import br.com.ernanilima.jmercado.service.componente.Legenda;
import br.com.ernanilima.jmercado.service.componente.Mascara;
import br.com.ernanilima.jmercado.service.constante.Mensagem;
import br.com.ernanilima.jmercado.service.constante.MensagemAlerta;
import br.com.ernanilima.jmercado.service.validacao.ValidarCampo;
import br.com.ernanilima.jmercado.suporte.UsuarioSuporte;
import br.com.ernanilima.jmercado.utils.Filtro;
import br.com.ernanilima.jmercado.utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    @Autowired private ApplicationContext springContext;
    @Autowired private BCryptPasswordEncoder passwdEncoder;
    @Autowired private InicioController cInicio;
    @Autowired private UsuarioService sUsuario;
    @Autowired private Utils utils;
    @Autowired private ValidarCampo vCampo;
    @Autowired private Legenda legenda;

    @Value("classpath:/fxml/login.fxml")
    private Resource R_FXML;
    @Value("classpath:/css/login.css")
    private Resource R_CSS;

    @FXML private TabPane tab;
    @FXML private Tab tpLogin;
    @FXML private TextField campoCodigo;
    @FXML private PasswordField campoSenha;
    @FXML private Button btnEntrar;
    @FXML private Button btnSair;
    @FXML private Button btnMudarSenha;
    @FXML private Tab tpMudarSenha;
    @FXML private PasswordField campoSenhaAtual;
    @FXML private PasswordField campoNovaSenha1;
    @FXML private PasswordField campoNovaSenha2;
    @FXML private Button btnGravar;
    @FXML private Button btnCancelar;
    @FXML private Label erroCodigo;
    @FXML private Label erroSenha;
    @FXML private Label erroSenhaAtual;
    @FXML private Label erroNovaSenha1;
    @FXML private Label erroNovaSenha2;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    private Usuario USUARIO_ATUAL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // MENSAGEM DE ERRO OCULTA
        erroCodigo.setVisible(false);
        erroSenha.setVisible(false);
        erroSenhaAtual.setVisible(false);
        erroNovaSenha1.setVisible(false);
        erroNovaSenha2.setVisible(false);

        // ACOES EM BOTOES
        btnEntrar.setOnAction(e -> verificarLoginRealizado());
        btnSair.setOnAction(e -> finalizar());
        btnMudarSenha.setOnAction(e -> abrirMudarSenha());
        btnGravar.setOnAction(e -> gravarMudarSenha());
        btnCancelar.setOnAction(e -> utils.exibirAba(tab, tpLogin, tpMudarSenha));

        // ACOS EM CAMPOS
        campoSenha.setOnKeyPressed(e -> {if(e.getCode() == KeyCode.ENTER) {verificarLoginRealizado();}});

        // TOOLTIP
        campoCodigo.setTooltip(new Tooltip(Mensagem.Usuario.CODIGO));
        campoSenha.setTooltip(new Tooltip(Mensagem.Usuario.SENHA));
        campoSenhaAtual.setTooltip(new Tooltip(Mensagem.Usuario.SENHA));
        campoNovaSenha1.setTooltip(new Tooltip(Mensagem.Usuario.SENHA_NOVA1));
        campoNovaSenha2.setTooltip(new Tooltip(Mensagem.Usuario.SENHA_NOVA2));

        // MASCARAS EM CAMPOS
        Mascara.numeroInteiro(campoCodigo, 4);
        Mascara.textoNumeroMaiusculo(campoSenha, 10);
        Mascara.textoNumeroMaiusculo(campoSenhaAtual, 10);
        Mascara.textoNumeroMaiusculo(campoNovaSenha1, 10);
        Mascara.textoNumeroMaiusculo(campoNovaSenha2, 10);

        // EXIBE A ABA PRINCIPAL E DESABILITA AS OUTRAS
        utils.exibirAba(tab, tpLogin, tpMudarSenha);
    }

    private void verificarLoginRealizado() {
        if (validarCamposLogin()) {
            USUARIO_ATUAL = new UsuarioSuporte().getUsuarioSuporte();
            // LOGIN DE SUPORTE, VERIFICA APENAS INTERNAMENTE
            if (Filtro.pInt(campoCodigo.getText()) == USUARIO_ATUAL.getCodigo() &
                    passwdEncoder.matches(campoSenha.getText(), USUARIO_ATUAL.getSenha())) {
                loginRealizado();
                return;
            }

            USUARIO_ATUAL = sUsuario.getPorId(Filtro.pInt(campoCodigo.getText()));
            // LOGIN DE USUARIO
            if (USUARIO_ATUAL != null && passwdEncoder.matches(campoSenha.getText(), USUARIO_ATUAL.getSenha())) {
                loginRealizado();
                return;
            }

            // LOGIN INVALIDO
            if (USUARIO_ATUAL == null || !passwdEncoder.matches(campoSenha.getText(), USUARIO_ATUAL.getSenha())) {
                legenda.exibirAlerta(MensagemAlerta.LOGIN_INVALIDO, campoCodigo, erroCodigo, erroSenha);
            }
        }
    }

    private void abrirMudarSenha() {
        if (validarCamposAbrirMudarSenha()) {
            USUARIO_ATUAL = sUsuario.getPorId(Filtro.pInt(campoCodigo.getText()));
            limpar();
            utils.exibirAba(tab, tpMudarSenha, tpLogin);
        }
    }

    private void gravarMudarSenha() {
        if (validarCamposGravarMudarSenha()) {
            // verifica se usuario atual eh nulo, pode ser nulo se for informado um codigo aleatorio na tela de login
            // verifica se o usuario atual tem uma senha
            // verifica se senha atual combina com senha do usuario
            // usado principalmente quando o usuario for nulo ou o usuario ter senha e a mesma nao combinar com senha digitada como atual
            if (USUARIO_ATUAL == null ||
                    USUARIO_ATUAL.getSenha() != null && !passwdEncoder.matches(campoSenhaAtual.getText(), USUARIO_ATUAL.getSenha())) {
                legenda.exibirAlerta(MensagemAlerta.SENHA_NAO_MODIFICADA, campoSenhaAtual, erroSenhaAtual);
                return;
            }

            USUARIO_ATUAL.setSenha(passwdEncoder.encode(campoNovaSenha1.getText()));
            sUsuario.gravar(USUARIO_ATUAL);
            loginRealizado();
        }
    }

    /** Exibe a tela do sistema para login realizado */
    private void loginRealizado() {
        limpar();
        cInicio.exibir(STAGE);
    }

    private void limpar() {
        utils.limparCampos(campoCodigo, campoSenha, campoSenhaAtual, campoNovaSenha1, campoNovaSenha2);
    }

    private boolean validarCamposLogin() {
        return vCampo.loginCodigoVazio(campoCodigo, campoCodigo.getPromptText(), erroCodigo) &&
                vCampo.loginSenhaVaziaInvalida(campoSenha, campoSenha.getPromptText(), 3, erroSenha);
    }

    private boolean validarCamposAbrirMudarSenha() {
        return vCampo.loginCodigoVazio(campoCodigo, campoCodigo.getPromptText(), erroCodigo);
    }

    private boolean validarCamposGravarMudarSenha() {
        return (USUARIO_ATUAL == null || USUARIO_ATUAL.getSenha() == null ||
                // se usuario nao for nulo e sua senha tambem nao for nula
                vCampo.loginSenhaVaziaInvalida(campoSenhaAtual, campoSenhaAtual.getPromptText(), 3, erroSenhaAtual)) &&

                vCampo.loginSenhaVaziaInvalida(campoNovaSenha1, campoNovaSenha1.getPromptText(), 3, erroNovaSenha1) &&
                vCampo.loginSenhaVaziaInvalida(campoNovaSenha2, campoNovaSenha2.getPromptText(), 3, erroNovaSenha2) &&
                vCampo.loginSenhasIguais(campoNovaSenha1, campoNovaSenha2, erroNovaSenha1, erroNovaSenha2);
    }

    public Usuario getUsuarioAtual() {
        return USUARIO_ATUAL;
    }

    /** Exibe tela de login
     * @param stage Stage - stage principal */
    public void exibir(Stage stage) {
        try {
            STAGE = stage;
            LOADER = new FXMLLoader(R_FXML.getURL());
            LOADER.setController(this);
            ROOT = LOADER.load();
            Scene scene = new Scene(ROOT);
            STAGE.setScene(scene);
            LOADER.setControllerFactory(x -> springContext.getBean(x));
            ROOT.getStylesheets().add(R_CSS.getURL().toExternalForm());
            STAGE.setResizable(true);
            /* MAXIMIZADO INICIO */
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            STAGE.setX(bounds.getMinX());
            STAGE.setY(bounds.getMinY());
            STAGE.setWidth(bounds.getWidth());
            STAGE.setHeight(bounds.getHeight());
            STAGE.setMaximized(true);
            /* MAXIMIZADO FIM */
            STAGE.show();
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void finalizar() {
        STAGE.close();
        Platform.exit();
    }
}
