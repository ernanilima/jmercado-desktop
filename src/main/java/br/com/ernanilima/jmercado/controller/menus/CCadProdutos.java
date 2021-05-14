package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadProdutos {

    @Autowired private InicioController cInicio;
    @Autowired private MenuController cMenu;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadProdutos = new Button();

    /** Configura o botao,
     * @return Button - botao */
    public Button getMenuC() {
        configurarBotao();

        return btnCCadProdutos;
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnCCadProdutos.getStyleClass().add("btnC");
        btnCCadProdutos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadProdutos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadProdutos.setText("Cadastrar Produtos");
    }
}
