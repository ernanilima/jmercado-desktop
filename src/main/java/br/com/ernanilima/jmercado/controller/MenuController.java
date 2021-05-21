package br.com.ernanilima.jmercado.controller;

import br.com.ernanilima.jmercado.controller.menus.ACadastros;
import br.com.ernanilima.jmercado.controller.menus.BCadProdutos;
import br.com.ernanilima.jmercado.controller.menus.BCadUsuarios;
import br.com.ernanilima.jmercado.controller.menus.suporte.MenuSuporte;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuController {

    @Autowired private MenuSuporte menuSuporte;
    @Autowired private ACadastros aCadastros;
    @Autowired private BCadProdutos bCadProdutos;
    @Autowired private BCadUsuarios bCadUsuarios;

    @Value("classpath:/css/menu.css")
    private Resource R_CSS;

    // Scroll para box de menu, caso possua muitos menus abertos
    private ScrollPane scroll = new ScrollPane();

    // Box para menus principais
    private VBox box0Menu = new VBox();

    // largura de botoes de menu
    public int getLarguraX() {return 250;}

    // altura de botoes de menu
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

    /** Adiciona o box dos menus principais ao menu lateral
     * OBS: projeto basico tem apenas a opcao de cadastros */
    private void menuPrincipal() {
        box0Menu.getChildren().addAll(
                aCadastros.getMenuA(),
                menuSuporte.getMenuSuporte()
        );
    }

    /** Minimiza todos os menus */
    public void minimizaTodos() {
        aCadastros.minimizarBox();
        bCadProdutos.minimizarBox();
        bCadUsuarios.minimizarBox();
    }
}
