package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.MenuController;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ACadastros {

    @Autowired private MenuController cMenu;
    @Autowired private BCadProdutos bCadProdutos;

    // botao principal do cadastro, abre outros botoes relacionados a cadastros
    private Button btnACadastros = new Button();

    // box onde estao os botoes relacionados a cadastros
    private VBox boxBCadastros = new VBox();

    /** Configura o botao principal de cadastros,
     * atribui acao ao botao principal e configura o box dos botoes secundarios
     * @return VBox - botao principal de cadastros, box com botoes secundarios */
    public VBox getMenuA() {
        listener();
        configurarBotao();
        configurarBox();

        return new VBox(btnACadastros, boxBCadastros);
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnACadastros.setOnAction(e -> minimizaMaximiza());
    }

    /** Constroi o botao principal do cadastro */
    private void configurarBotao() {
        // BOTAO CADASTROS
        btnACadastros.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnACadastros.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnACadastros.setText("Cadastros");
    }

    /** Configura o box onde estao os botoes relacionados a cadastros */
    private void configurarBox() {
        boxBCadastros.setPrefHeight(0);
        boxBCadastros.setVisible(false);
    }

    /** Verifica se o box esta minimizado ou nao
     * Executa uma acao de acordo com o estado do box */
    private void minimizaMaximiza() {
        if (boxBCadastros.isVisible()) {
            cMenu.minimizaTodos();
        } else {
            maximizarBox();
        }
    }

    /** Apaga tudo do box */
    public void minimizarBox() {
        boxBCadastros.setPrefHeight(0);
        boxBCadastros.setVisible(false);
        boxBCadastros.getChildren().removeAll(
                bCadProdutos.getMenuB()
        );
    }

    /** Adiciona todos os botoes de cadastros ao box */
    private void maximizarBox() {
        boxBCadastros.setPrefHeight(Control.USE_COMPUTED_SIZE);
        boxBCadastros.setVisible(true);
        boxBCadastros.getChildren().addAll(
                bCadProdutos.getMenuB()
        );
    }
}
