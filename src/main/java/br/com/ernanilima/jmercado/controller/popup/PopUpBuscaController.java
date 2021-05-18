package br.com.ernanilima.jmercado.controller.popup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class PopUpBuscaController implements Initializable {

    @Value("classpath:/fxml/popup/buscar.fxml")
    private Resource R_FXML;
    @Value("classpath:/css/popup.css")
    private Resource R_CSS;

    @FXML private Label mensagem;
    @FXML private TextField campoBusca;
    @FXML private Button btnBuscar;
    @FXML private Button btnCancelar;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;
    private Scene SCENE;

    private String RESULTADO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        STAGE = new Stage();

        // ACOES EM BOTOES
        btnBuscar.setOnAction(e -> buscar());
        btnCancelar.setOnAction(e -> cancelar());
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
            STAGE.setOnCloseRequest(e -> cancelar());

            campoBusca.requestFocus();
            this.mensagem.setText(mensagem);

            STAGE.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Quando clicado no botao buscar */
    private void buscar() {
        RESULTADO = campoBusca.getText();
        sair();
    }

    /** Quando clicado no botao cancelar ou fechar o dialog */
    private void cancelar() {
        RESULTADO = null;
        sair();
    }

    /** Resultado ao pressionar botao no dialog
     * @return String - informacao digitada no campo de busca */
    public String getRsultado() {
        return RESULTADO;
    }

    private void sair() {
        STAGE.close();
        STAGE = null;
    }
}
