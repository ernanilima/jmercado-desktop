package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.MenuController;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BCadProdutos {

    @Autowired private MenuController cMenu;

    // botao que abre outros botoes relacionados a cadastro de produto
    private Button btnBCadProdutos = new Button();

    /** Configura o botao de cadastro de produtos,
     * @return VBox - botao de cadastro de produtos */
    public VBox getMenuB() {
        configurarBotao();

        return new VBox(btnBCadProdutos);
    }

    /** Constroi o botao de cadastro de produtos */
    private void configurarBotao() {
        btnBCadProdutos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setText("Cadastro De Produtos");
    }
}
