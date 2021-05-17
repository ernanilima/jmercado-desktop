package br.com.ernanilima.jmercado.controller.popup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class PopUpConfirmacaoController implements Initializable {

    @Value("classpath:/fxml/popup/confirmacao.fxml")
    private Resource R_FXML;

    @FXML private Label mensagem;
    @FXML private Button btnSim;
    @FXML private Button btnNao;

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;
    private Scene SCENE;

    private boolean RESULTADO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        STAGE = new Stage();

        // ACOES EM BOTOES
        btnSim.setOnAction(e -> {RESULTADO = true; sair();});
        btnNao.setOnAction(e -> {RESULTADO = false; sair();});

    }

    /** Exibe a janela */
    public void exibirPopUp(String mensagem) {
        try {
            LOADER = new FXMLLoader(R_FXML.getURL());
            LOADER.setController(this);
            ROOT = LOADER.load();
            SCENE = new Scene(ROOT);
            STAGE.setScene(SCENE);
            STAGE.setResizable(false);
            STAGE.initModality(Modality.APPLICATION_MODAL);

            // se for fechado sem clicar em botao
            STAGE.setOnCloseRequest(e -> {RESULTADO = false; sair();});

            btnNao.requestFocus();
            this.mensagem.setText(mensagem);

            // OBTEM O TAMANHO UTILIZAVEL DA TELA
//            Screen screen = Screen.getPrimary();
//            Rectangle2D bounds = screen.getVisualBounds();
//            STAGE.setOnShown(e -> {
//                // POSICIONA O POPUP
//                STAGE.setX((bounds.getMaxX() - STAGE.getWidth()) / 2);
//                STAGE.setY(bounds.getMaxY() - STAGE.getHeight() - 50);
//            });

            STAGE.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getRsultado() {
        return RESULTADO;
    }

    private void sair() {
        STAGE.close();
        STAGE = null;
    }
}
