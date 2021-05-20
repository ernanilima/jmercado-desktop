package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.DepartamentoController;
import br.com.ernanilima.jmercado.controller.InicioController;
import br.com.ernanilima.jmercado.controller.MenuController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CCadDepartamentos {

    @Autowired private InicioController cInicio;
    @Autowired private DepartamentoController cDepartamento;
    @Autowired private MenuController cMenu;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao que abre o controller referente ao cadastro
    private Button btnCCadDepartamentos = new Button();
    private VBox boxDoMenu = new VBox(btnCCadDepartamentos);

    /** Configura o botao,
     * @return VBox - menu */
    public VBox getMenuC() {
        listener();
        configurarBotao();

        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnCCadDepartamentos, Liberacoes.CADASTROS_PRODUTOS_DEPARTAM, boxDoMenu);

        return boxDoMenu;
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnCCadDepartamentos.setOnAction(e -> {
            cMenu.minimizaTodos();
            cInicio.setPainelCentral(cDepartamento.getPainel());
        });
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnCCadDepartamentos.getStyleClass().add("btnC");
        btnCCadDepartamentos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadDepartamentos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnCCadDepartamentos.setText("Cadastrar Departamentos");
    }
}
