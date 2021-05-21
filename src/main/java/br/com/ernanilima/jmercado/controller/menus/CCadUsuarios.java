package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import br.com.ernanilima.jmercado.controller.UsuarioController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadUsuarios {

    @Autowired private InicioController cInicio;
    @Autowired private UsuarioController cUsuario;
    @Autowired private MenuController cMenu;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadUsuario = new Button();
    private VBox boxDoMenu = new VBox(btnCCadUsuario);

    /** Configura o botao,
     * @return VBox - menu */
    public VBox getMenuC() {
        liberacaoParaBotao();
        listener();
        configurarBotao();

        return boxDoMenu;
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

    /** Liberacao para o menu */
    private void liberacaoParaBotao() {
        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCCadUsuario, Liberacoes.CADASTROS_USUARIOS_USUARIOS, boxDoMenu);
    }
}
