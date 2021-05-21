package br.com.ernanilima.jmercado.controller.menus.suporte;

import br.com.ernanilima.jmercado.controller.LoginController;
import br.com.ernanilima.jmercado.controller.MenuController;
import br.com.ernanilima.jmercado.controller.popup.PopUpMensagemController;
import br.com.ernanilima.jmercado.suporte.validacao.ValidarUsuarioSuporte;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MenuSuporte {

    @Autowired private LoginController cLogin;
    @Autowired private MenuController cMenu;

    @Autowired private PopUpMensagemController ppMensagem;

    private Button btnMenuSuporte = new Button();
    private VBox boxDoMenu = new VBox(btnMenuSuporte);

    /** Configura o botao,
     * @return VBox - menu */
    public VBox getMenuSuporte() {
        listener();
        configurarBotao();

        return (ValidarUsuarioSuporte.conectado(cLogin.getUsuarioAtual())) ? boxDoMenu : new VBox();
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnMenuSuporte.setOnAction(e -> {
            ppMensagem.exibirPopUp("MENU DE SUPORTE PENDENTE!");
        });
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnMenuSuporte.getStyleClass().add("btnSuporte");
        btnMenuSuporte.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnMenuSuporte.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnMenuSuporte.setText("Suporte");
    }
}
