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

    @Autowired private ACadastros menuACadastro;

    @Value("classpath:/css/menu.css")
    private Resource R_CSS;

    private ScrollPane scroll = new ScrollPane();
    private VBox box0Menu = new VBox();

    public int getLarguraX() {return 250;}
    public int getAlturaY() {return 40;}

    //CONSTRUTOR
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

    private void menuPrincipal() {
        box0Menu.getChildren().add(
                menuACadastro.obterMenuA()
        );
    }
}
