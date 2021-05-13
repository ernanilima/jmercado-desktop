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
    @Autowired private CCadDepartamentos cCadDepartamentos;

    // botao que abre outros botoes relacionados a cadastro de produto
    private Button btnBCadProdutos = new Button();
    private VBox boxCCadProdutos = new VBox();

    /** Configura o botao de cadastro de produtos,
     * @return VBox - botao de cadastro de produtos */
    public VBox getMenuB() {
        listener();
        configurarBotao();
        configurarBox();

        return new VBox(btnBCadProdutos, boxCCadProdutos);
    }

    private void listener() {
        btnBCadProdutos.setOnAction(e -> minimizaMaximiza());
    }

    /** Constroi o botao de cadastro de produtos */
    private void configurarBotao() {
        btnBCadProdutos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setText("Cadastro De Produtos");
    }

    private void configurarBox() {
        boxCCadProdutos.setPrefHeight(0);
        boxCCadProdutos.setVisible(false);
    }

    private void minimizaMaximiza() {
        if (boxCCadProdutos.isVisible()) {
            minimizarBox();
        } else {
            maximizarBox();
        }
    }

    public void minimizarBox() {
        boxCCadProdutos.setPrefHeight(0);
        boxCCadProdutos.setVisible(false);
        boxCCadProdutos.getChildren().removeAll(
                cCadDepartamentos.getMenuC()
        );
    }
    private void maximizarBox() {
        boxCCadProdutos.setPrefHeight(Control.USE_COMPUTED_SIZE);
        boxCCadProdutos.setVisible(true);
        boxCCadProdutos.getChildren().addAll(
                cCadDepartamentos.getMenuC()
        );
    }
}
