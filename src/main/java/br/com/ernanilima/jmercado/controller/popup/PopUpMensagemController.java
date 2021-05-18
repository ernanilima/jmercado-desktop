package br.com.ernanilima.jmercado.controller.popup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class PopUpMensagemController implements Initializable {

    @Value("classpath:/fxml/popup/mensagem.fxml")
    private Resource R_FXML;
    @Value("classpath:/css/popup.css")
    private Resource R_CSS;

    @FXML private Label mensagem;
    @FXML private Button btnOk;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;
    private Scene SCENE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        STAGE = new Stage();

        // ACOES EM BOTOES
        btnOk.setOnAction(e -> sair());
    }

    /** Exibe como dialog */
    public void exibirPopUp(String mensagem) {
        try {
            LOADER = new FXMLLoader(R_FXML.getURL());
            LOADER.setController(this);
            ROOT = LOADER.load();
            ROOT.getStylesheets().add(R_CSS.getURL().toExternalForm());
            SCENE = new Scene(ROOT);
            STAGE.setScene(SCENE);
            STAGE.setResizable(false);
            STAGE.initModality(Modality.APPLICATION_MODAL);

            // se for fechado sem clicar em botao
            STAGE.setOnCloseRequest(event -> sair());

            btnOk.requestFocus();
            this.mensagem.setText(mensagem);

            STAGE.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sair() {
        STAGE.close();
        STAGE = null;
    }
}
