package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import br.com.ernanilima.jmercado.controller.UsuarioController;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadUsuarios {

    @Autowired private InicioController cInicio;
    @Autowired private UsuarioController cUsuario;
    @Autowired private MenuController cMenu;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadUsuario = new Button();

    /** Configura o botao,
     * @return Button - botao */
    public Button getMenuC() {
        listener();
        configurarBotao();

        return btnCCadUsuario;
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnCCadUsuario.setOnAction(e -> {
            cMenu.minimizaTodos();
            cInicio.setPainelCentral(cUsuario.getPainel());
        });
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnCCadUsuario.getStyleClass().add("btnC");
        btnCCadUsuario.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadUsuario.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadUsuario.setText("Cadastrar Usuários");
    }
}
