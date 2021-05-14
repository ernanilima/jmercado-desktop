package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadDepartamentos {

    @Autowired private InicioController cInicio;
    @Autowired private MenuController cMenu;

    // botao que abre o cadastro de departamentos
    private Button btnCCadDepartamentos = new Button();

    /** Configura o botao de cadastro de departamentos,
     * @return VBox - botao de cadastro de departamentos */
    public Button getMenuC() {
        configurarBotao();

        return btnCCadDepartamentos;
    }

    /** Constroi o botao de cadastro de departamentos */
    private void configurarBotao() {
        btnCCadDepartamentos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadDepartamentos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadDepartamentos.setText("Cadastrar Departamentos");
    }
}
