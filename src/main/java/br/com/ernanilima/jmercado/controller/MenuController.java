package br.com.ernanilima.jmercado.controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController {

    private ScrollPane scroll = new ScrollPane();
    private VBox box0Menu = new VBox();

    public int getLarguraX() {return 250;}
    public int getAlturaY() {return 40;}

    //CONSTRUTORES
    protected ScrollPane getMenu() {
            scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scroll.setContent(box0Menu);
        return scroll;
    }
}
