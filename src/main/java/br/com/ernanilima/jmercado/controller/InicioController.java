package br.com.ernanilima.jmercado.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
public class InicioController implements Initializable {

    @Autowired private ApplicationContext springContext;
    @Autowired private LoginController cLogin;
    @Autowired private MenuController cMenu;

    @Value("classpath:/fxml/inicio.fxml")
    private Resource R_FXML;
    @Value("classpath:/css/style.css")
    private Resource R_CSS;

    @FXML private BorderPane borderPane;
    @FXML private BorderPane borderPaneCentral;
    @FXML private Label campoLegenda;
    @FXML private Label campoLegendaAlerta;
    @FXML private Label titulo;
    @FXML private MenuButton btnUsuario;
    private MenuItem itemTrocarUsuario = new MenuItem();

    private Stage STAGE;
    private FXMLLoader LOADER;
    private Parent ROOT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoLegendaAlerta.setVisible(false);
        borderPane.setLeft(cMenu.geMenuLateral());

        btnUsuario.setText(cLogin.getUsuarioAtual().getNomeSistema());
        itemTrocarUsuario.setText("Trocar Usuário");
        btnUsuario.getItems().add(itemTrocarUsuario);

        //ACOES EM BOTOES
        itemTrocarUsuario.setOnAction(e -> {cMenu.minimizaTodos(); cLogin.exibir(STAGE);});
    }

    public void setTitulo(Label tituloModal, String tituloSistema) {
        if (tituloModal.isVisible()) {
            tituloModal.setText(tituloSistema);
        } else {
            titulo.setText(tituloSistema);
        }
    }

    public void setPainelCentral(Node painel) {
        borderPaneCentral.setCenter(painel);
    }

    /** Exibe legenda no fxml inicial
     * Usado apenas em {@link br.com.ernanilima.jmercado.service.componente.Legenda}
     * @param legenda String - legenda que deve ser exibida */
    public void setLegenda(String legenda) {
        campoLegenda.setText(legenda);
    }

    /** Campo de alerta de erro, sobrepoe a legenda
     * @return Label - campo de alerta de erro */
    public Label getCampoAlerta() {
        return campoLegendaAlerta;
    }

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
}
