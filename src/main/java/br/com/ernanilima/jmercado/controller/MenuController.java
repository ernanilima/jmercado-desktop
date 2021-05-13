package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.menus.ACadastros;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuController {

    @Autowired private ACadastros menuACadastros;

    @Value("classpath:/css/menu.css")
    private Resource R_CSS;

    // Scroll para box de menu, caso possua muitos menus abertos
    // permite que seja possivel usar o scroll
    private ScrollPane scroll = new ScrollPane();

    // Box dos menus principais
    private VBox box0Menu = new VBox();

    // largura do botao de menu
    public int getLarguraX() {return 250;}

    // altura do botao de menu
    public int getAlturaY() {return 40;}

    /** Constroi o menu lateral
     * @return ScrollPane - menu lateral */
    protected ScrollPane menuLateral() {
        try {
            scroll.getStyleClass().add("scroll");
            scroll.getStylesheets().add(R_CSS.getURL().toExternalForm());
            scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setContent(box0Menu);

            menuPrincipal();
        } catch (IOException e) { e.printStackTrace(); }
        return scroll;
    }

    /** Adiciona os menus principais ao menu lateral */
    private void menuPrincipal() {
        box0Menu.getChildren().add(
                menuACadastros.getMenuA()
        );
    }

    /** Minimiza todos os menus */
    public void minimizaTodos() {
        menuACadastros.minimizarBox();
    }
}
