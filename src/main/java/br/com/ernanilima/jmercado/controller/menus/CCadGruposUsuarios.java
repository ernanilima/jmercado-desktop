package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.GrupoUsuarioController;
import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadGruposUsuarios {

    @Autowired private InicioController cInicio;
    @Autowired private GrupoUsuarioController cGrupoUsuario;
    @Autowired private MenuController cMenu;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadGruposUsuarios = new Button();
    private VBox boxDoMenu = new VBox(btnCCadGruposUsuarios);

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
        btnCCadGruposUsuarios.setOnAction(e -> {
            cMenu.minimizaTodos();
            cInicio.setPainelCentral(cGrupoUsuario.getPainel());
        });
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnCCadGruposUsuarios.getStyleClass().add("btnC");
        btnCCadGruposUsuarios.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadGruposUsuarios.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadGruposUsuarios.setText("Cadastrar Grupos De Usuários");
    }

    /** Liberacao para o menu */
    private void liberacaoParaBotao() {
        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCCadGruposUsuarios, Liberacoes.CADASTROS_USUARIOS_GRUPOS_USUARIOS, boxDoMenu);
    }
}
