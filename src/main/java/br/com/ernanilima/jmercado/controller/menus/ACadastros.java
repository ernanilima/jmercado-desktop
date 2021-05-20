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
public class ACadastros {

    @Autowired private MenuController cMenu;
    @Autowired private BCadProdutos bCadProdutos;
    @Autowired private BCadUsuarios bCadUsuarios;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao principal, abre o box com outros botoes relacionados
    private Button btnACadastros = new Button();

    // box com botoes relacionados
    private VBox boxBCadastros = new VBox();
    private VBox boxDoMenu = new VBox(btnACadastros, boxBCadastros);

    /** Configura o botao principal,
     * @return VBox - botao principal, box com botoes relacionados */
    public VBox getMenuA() {
        listener();
        configurarBotao();
        configurarBox();

        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnACadastros, Liberacoes.CADASTROS, boxDoMenu);

        return boxDoMenu;
    }

    /** Acao ao pressionar botao */
    private void listener() {
        btnACadastros.setOnAction(e -> minimizaMaximiza());
    }

    /** Constroi o botao principal */
    private void configurarBotao() {
        btnACadastros.getStyleClass().add("btnA");
        btnACadastros.setId("btn_mais");
        btnACadastros.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnACadastros.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnACadastros.setText("Cadastros");
    }

    /** Configura o box com os botoes relacionados */
    private void configurarBox() {
        boxBCadastros.setPrefHeight(0);
        boxBCadastros.setVisible(false);
    }

    /** Verifica se o box com botoes relacionados esta visivil ou nao */
    private void minimizaMaximiza() {
        if (boxBCadastros.isVisible()) {
            cMenu.minimizaTodos();
        } else {
            maximizarBox();
        }
    }

    /** Apaga tudo do box com botoes relacionados */
    public void minimizarBox() {
        boxBCadastros.setPrefHeight(0);
        boxBCadastros.setVisible(false);
        boxBCadastros.getChildren().removeAll(getMenusB());
    }

    /** Adiciona todos os botoes relacionados ao box */
    private void maximizarBox() {
        boxBCadastros.setPrefHeight(Control.USE_COMPUTED_SIZE);
        boxBCadastros.setVisible(true);
        boxBCadastros.getChildren().addAll(getMenusB());
    }

    /** Todos os botoes secundarios
     * @return VBox[] - menus B */
    private VBox[] getMenusB() {
        return new VBox[] {
                bCadProdutos.getMenuB(),
                bCadUsuarios.getMenuB()
        };
    }
}
