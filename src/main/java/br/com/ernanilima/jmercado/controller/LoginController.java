package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.listener.FocusListener;
import br.com.ernanilima.jmercado.controller.listener.KeyListener;
import br.com.ernanilima.jmercado.model.Usuario;
import br.com.ernanilima.jmercado.service.UsuarioService;
import br.com.ernanilima.jmercado.service.componente.Mascara;
import br.com.ernanilima.jmercado.service.constante.Mensagem;
import br.com.ernanilima.jmercado.service.validacao.ValidarCampo;
import br.com.ernanilima.jmercado.suporte.UsuarioSuporte;
import br.com.ernanilima.jmercado.utils.Filtro;
import br.com.ernanilima.jmercado.utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
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
    @Autowired private FocusListener lFocus;
    @Autowired private KeyListener lKey;
    @Autowired private UsuarioService sUsuario;
    @Autowired private Utils utils;
    @Autowired private ValidarCampo vCampo;

    @Value("classpath:/fxml/login.fxml")
    private Resource R_FXML;

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

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;
    private Scene SCENE;

    private Usuario USUARIO_ATUAL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();

        // ACOES EM BOTOES
        btnEntrar.setOnAction(e -> loginRealizado());

        // ACOES DE FOCO
        campoCodigo.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.CODIGO));
        campoSenha.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.SENHA));
        campoSenhaAtual.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.SENHA));
        campoNovaSenha1.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.SENHA_NOVA1));
        campoNovaSenha2.focusedProperty().addListener(lFocus.exibeLegendaActionListener(Mensagem.Usuario.SENHA_NOVA2));

        // MASCARAS EM CAMPOS
        Mascara.numeroInteiro(campoCodigo, 4);
        Mascara.textoNumeroMaiusculo(campoSenha, 10);
        Mascara.textoNumeroMaiusculo(campoSenhaAtual, 10);
        Mascara.textoNumeroMaiusculo(campoNovaSenha1, 10);
        Mascara.textoNumeroMaiusculo(campoNovaSenha2, 10);

        // EXIBE A ABA PRINCIPAL E DESABILITA AS OUTRAS
        utils.exibirAba(tab, tpLogin, tpMudarSenha);
    }

    private void loginRealizado() {
        if (validarCampos()) {
            USUARIO_ATUAL = new UsuarioSuporte().getUsuarioSuporte();

            // LOGIN DE SUPORTE, VERIFICA APENAS INTERNAMENTE
            if (Filtro.pInt(campoCodigo.getText()) == USUARIO_ATUAL.getCodigo() &
                    passwdEncoder.matches(campoSenha.getText(), USUARIO_ATUAL.getSenha())) {
                System.out.println("login de suporte realizado");
                return;
            }

            USUARIO_ATUAL = sUsuario.getPorId(Filtro.pInt(campoCodigo.getText()));

            // LOGIN DE USUARIO
            if (USUARIO_ATUAL != null && passwdEncoder.matches(campoSenha.getText(), USUARIO_ATUAL.getSenha())) {
                System.out.println("login do usuario realizado");
                return;
            }

            // LOGIN SEM SENHA
            if (USUARIO_ATUAL != null && USUARIO_ATUAL.getSenha() == null) {
                System.out.println("necessario mudar a senha do usuario");
                return;
            }

            // LOGIN INVALIDO
            if (USUARIO_ATUAL == null || !passwdEncoder.matches(campoSenha.getText(), USUARIO_ATUAL.getSenha())) {
                System.out.println("login invalido");
            }
        }
    }

    private boolean validarCampos() {
        return vCampo.campoVazio(campoCodigo, new Label(campoCodigo.getPromptText())) &&
                vCampo.campoVazio(campoSenha, new Label(campoSenha.getPromptText()));
    }

    public void exibirModal() {
        try {
            LOADER = new FXMLLoader(R_FXML.getURL());
            LOADER.setController(this);
            ROOT = LOADER.load();
            SCENE = new Scene(ROOT);
            STAGE.setScene(SCENE);
            STAGE.setResizable(false);
            STAGE.initModality(Modality.APPLICATION_MODAL);
            STAGE.setOnCloseRequest(e -> finalizar());
            LOADER.setControllerFactory(aClass -> springContext.getBean(aClass));
            STAGE.showAndWait();
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void finalizar() {
        STAGE.close();
        Platform.exit();
    }
}
