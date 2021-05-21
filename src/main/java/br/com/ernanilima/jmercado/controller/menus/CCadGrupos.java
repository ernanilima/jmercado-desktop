package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadGrupos {

    @Autowired private InicioController cInicio;
    @Autowired private MenuController cMenu;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadGrupos = new Button();
    private VBox boxDoMenu = new VBox(btnCCadGrupos);

    /** Configura o botao,
     * @return VBox - menu */
    public VBox getMenuC() {
        liberacaoParaBotao();
        configurarBotao();

        return boxDoMenu;
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnCCadGrupos.getStyleClass().add("btnC");
        btnCCadGrupos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadGrupos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadGrupos.setText("Cadastrar Grupos");
    }

    /** Liberacao para o menu */
    private void liberacaoParaBotao() {
        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCCadGrupos, Liberacoes.CADASTROS_PRODUTOS_GRUPO, boxDoMenu);
    }
}
