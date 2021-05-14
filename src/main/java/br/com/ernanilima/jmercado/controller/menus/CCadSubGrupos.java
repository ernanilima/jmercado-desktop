package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadSubGrupos {

    @Autowired private InicioController cInicio;
    @Autowired private MenuController cMenu;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadSubGrupos = new Button();

    /** Configura o botao,
     * @return Button - botao */
    public Button getMenuC() {
        configurarBotao();

        return btnCCadSubGrupos;
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnCCadSubGrupos.getStyleClass().add("btnC");
        btnCCadSubGrupos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadSubGrupos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadSubGrupos.setText("Cadastrar SubGrupos");
    }
}
