package br.com.ernanilima.jmercado.controller;

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
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginController implements Initializable {

    @Autowired private ApplicationContext springContext;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();
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
