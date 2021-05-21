package br.com.ernanilima.jmercado.controller.menus;

import br.com.ernanilima.jmercado.controller.MenuController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.validacao.ValidarLiberacao;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BCadUsuarios {

    @Autowired private MenuController cMenu;
    @Autowired private CCadGruposUsuarios cCadGruposUsuarios;
    @Autowired private CCadUsuarios cCadUsuarios;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao que abre o box com outros botoes relacionados
    private Button btnBCadUsuarios = new Button();

    // box com botoes relacionados
    private VBox boxCCadUsuarios = new VBox();
    private VBox boxDoMenu = new VBox(btnBCadUsuarios, boxCCadUsuarios);

    /** Configura o botao,
     * @return VBox - botao, box com botoes relacionados */
    public VBox getMenuB() {
        liberacaoParaBotao();
        listener();
        configurarBotao();
        configurarBox();

        return boxDoMenu;
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnBCadUsuarios.setOnAction(e -> minimizaMaximiza());
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnBCadUsuarios.getStyleClass().add("btnB");
        btnBCadUsuarios.setId("btn_mais");
        btnBCadUsuarios.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadUsuarios.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadUsuarios.setText("Cadastro De Usuários");
    }

    /** Configura o box com os botoes relacionados */
    private void configurarBox() {
        boxCCadUsuarios.setPrefHeight(0);
        boxCCadUsuarios.setVisible(false);
    }

    /** Verifica se o box com botoes relacionados esta visivil ou nao */
    private void minimizaMaximiza() {
        if (boxCCadUsuarios.isVisible()) {
            minimizarBox();
        } else {
            maximizarBox();
        }
    }

    /** Apaga tudo do box com botoes relacionados */
    public void minimizarBox() {
        boxCCadUsuarios.setPrefHeight(0);
        boxCCadUsuarios.setVisible(false);
        boxCCadUsuarios.getChildren().removeAll(getMenusC());
    }

    /** Adiciona todos os botoes relacionados ao box */
    private void maximizarBox() {
        boxCCadUsuarios.setPrefHeight(Control.USE_COMPUTED_SIZE);
        boxCCadUsuarios.setVisible(true);
        boxCCadUsuarios.getChildren().addAll(getMenusC());
    }

    /** Todos os botoes secundarios
     * @return VBox[] - menus C */
    private VBox[] getMenusC() {
        return new VBox[] {
                cCadGruposUsuarios.getMenuC(),
                cCadUsuarios.getMenuC()
        };
    }

    /** Liberacao para o menu */
    private void liberacaoParaBotao() {
        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnBCadUsuarios, Liberacoes.CADASTROS_USUARIOS, boxDoMenu);
    }
}
