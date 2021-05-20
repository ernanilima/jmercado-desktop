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
public class CCadSubGrupos {

    @Autowired private InicioController cInicio;
    @Autowired private MenuController cMenu;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadSubGrupos = new Button();
    private VBox boxDoMenu = new VBox(btnCCadSubGrupos);

    /** Configura o botao,
     * @return VBox - menu */
    public VBox getMenuC() {
        configurarBotao();

        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCCadSubGrupos, Liberacoes.CADASTROS_PRODUTOS_SUBGRUPO, boxDoMenu);

        return boxDoMenu;
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnCCadSubGrupos.getStyleClass().add("btnC");
        btnCCadSubGrupos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadSubGrupos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadSubGrupos.setText("Cadastrar SubGrupos");
    }
}
