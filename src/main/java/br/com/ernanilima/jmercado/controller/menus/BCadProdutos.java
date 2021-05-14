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
    @Autowired private CCadGrupos cCadGrupos;
    @Autowired private CCadSubGrupos cCadSubGrupos;
    @Autowired private CCadProdutos cCadProdutos;

    // botao que abre o box com outros botoes relacionados
    private Button btnBCadProdutos = new Button();

    // box com botoes relacionados
    private VBox boxCCadProdutos = new VBox();

    /** Configura o botao,
     * @return VBox - botao, box com botoes relacionados */
    public VBox getMenuB() {
        listener();
        configurarBotao();
        configurarBox();

        return new VBox(btnBCadProdutos, boxCCadProdutos);
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnBCadProdutos.setOnAction(e -> minimizaMaximiza());
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnBCadProdutos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setText("Cadastro De Produtos");
    }

    /** Configura o box com os botoes relacionados */
    private void configurarBox() {
        boxCCadProdutos.setPrefHeight(0);
        boxCCadProdutos.setVisible(false);
    }

    /** Verifica se o box com botoes relacionados esta visivil ou nao */
    private void minimizaMaximiza() {
        if (boxCCadProdutos.isVisible()) {
            minimizarBox();
        } else {
            maximizarBox();
        }
    }

    /** Apaga tudo do box com botoes relacionados */
    public void minimizarBox() {
        boxCCadProdutos.setPrefHeight(0);
        boxCCadProdutos.setVisible(false);
        boxCCadProdutos.getChildren().removeAll(botoesDeMenuC());
    }

    /** Adiciona todos os botoes relacionados ao box */
    private void maximizarBox() {
        boxCCadProdutos.setPrefHeight(Control.USE_COMPUTED_SIZE);
        boxCCadProdutos.setVisible(true);
        boxCCadProdutos.getChildren().addAll(botoesDeMenuC());
    }

    /** Todos os botoes secundarios
     * @return Button[] - botoes de menu c */
    private Button[] botoesDeMenuC() {
        return new Button[] {
                cCadDepartamentos.getMenuC(),
                cCadGrupos.getMenuC(),
                cCadSubGrupos.getMenuC(),
                cCadProdutos.getMenuC()
        };
    }
}
