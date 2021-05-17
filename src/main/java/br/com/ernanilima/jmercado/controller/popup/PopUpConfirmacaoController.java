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
public class PopUpConfirmacaoController implements Initializable {

    @Value("classpath:/fxml/popup/confirmacao.fxml")
    private Resource R_FXML;
    @Value("classpath:/css/popup.css")
    private Resource R_CSS;

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

    /** Exibe como dialog */
    public void exibirPopUp(CorPopUp corParaSimNao, String mensagem) {
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
            STAGE.setOnCloseRequest(e -> {RESULTADO = false; sair();});

            btnNao.requestFocus();
            this.mensagem.setText(mensagem);

            setCorBtn(corParaSimNao);
            STAGE.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Define class para cores no css
     * @param corParaSimNao CorPopUp - cores dos botoes */
    private void setCorBtn(CorPopUp corParaSimNao) {
        switch (corParaSimNao) {
            case VERDE_VERMELHO:
                btnSim.getStyleClass().add("btnVerde");
                btnNao.getStyleClass().add("btnVermelho");
                break;
            case VERMELHO_VERDE:
                btnSim.getStyleClass().add("btnVermelho");
                btnNao.getStyleClass().add("btnVerde");
                break;
        }
    }

    /** Resultado ao pressionar botao no dialog
     * @return boolean - true se clicado em SIM */
    public boolean getRsultado() {
        return RESULTADO;
    }

    private void sair() {
        STAGE.close();
        STAGE = null;
    }
}
