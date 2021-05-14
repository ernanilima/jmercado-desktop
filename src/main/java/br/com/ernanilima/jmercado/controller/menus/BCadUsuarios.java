package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.MenuController;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BCadUsuarios {

    @Autowired private MenuController cMenu;
    @Autowired private CCadGruposUsuarios cCadGruposUsuarios;

    // botao que abre outros botoes relacionados a cadastro de usuarios
    private Button btnBCadUsuarios = new Button();

    // box onde estao os botoes relacionados a cadastro de usuarios
    private VBox boxCCadUsuarios = new VBox();

    /** Configura o botao de cadastro de usuarios,
     * @return VBox - botao de cadastro de usuarios */
    public VBox getMenuB() {
        listener();
        configurarBotao();
        configurarBox();

        return new VBox(btnBCadUsuarios, boxCCadUsuarios);
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnBCadUsuarios.setOnAction(e -> minimizaMaximiza());
    }

    /** Constroi o botao de cadastro de produtos */
    private void configurarBotao() {
        btnBCadUsuarios.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadUsuarios.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadUsuarios.setText("Cadastro De Usuários");
    }

    /** Configura o box onde estao os botoes relacionados a cadastro de produtos */
    private void configurarBox() {
        boxCCadUsuarios.setPrefHeight(0);
        boxCCadUsuarios.setVisible(false);
    }

    /** Verifica se o box com botos secundarios esta visivil ou nao
     * Executa uma acao de acordo com o estado do box */
    private void minimizaMaximiza() {
        if (boxCCadUsuarios.isVisible()) {
            minimizarBox();
        } else {
            maximizarBox();
        }
    }

    /** Apaga tudo do box */
    public void minimizarBox() {
        boxCCadUsuarios.setPrefHeight(0);
        boxCCadUsuarios.setVisible(false);
        boxCCadUsuarios.getChildren().removeAll(
                cCadGruposUsuarios.getMenuC()
        );
    }

    /** Adiciona todos os botoes de cadastro de produto ao box */
    private void maximizarBox() {
        boxCCadUsuarios.setPrefHeight(Control.USE_COMPUTED_SIZE);
        boxCCadUsuarios.setVisible(true);
        boxCCadUsuarios.getChildren().addAll(
                cCadGruposUsuarios.getMenuC()
        );
    }
}
