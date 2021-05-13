package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.MenuController;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ACadastros {

    @Autowired private MenuController cMenu;

    private Button btnACadastros = new Button();
    private VBox box1Cadastros = new VBox();

    public VBox obterMenuA() {
        configurarBotao();

        return new VBox(btnACadastros);
    }

    private void configurarBotao() {
        // BOTAO CADASTROS
        btnACadastros.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnACadastros.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnACadastros.setText("Cadastros");
    }
}
