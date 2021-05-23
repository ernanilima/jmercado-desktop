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
public class BCadProdutos {

    @Autowired private MenuController cMenu;
    @Autowired private CCadDepartamentos cCadDepartamentos;
    @Autowired private CCadGrupos cCadGrupos;
    @Autowired private CCadSubgrupos cCadSubgrupos;
    @Autowired private CCadProdutos cCadProdutos;
    @Autowired private ValidarLiberacao vLiberacao;

    // botao que abre o box com outros botoes relacionados
    private Button btnBCadProdutos = new Button();

    // box com botoes relacionados
    private VBox boxCCadProdutos = new VBox();
    private VBox boxDoMenu = new VBox(btnBCadProdutos, boxCCadProdutos);

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
        btnBCadProdutos.setOnAction(e -> minimizaMaximiza());
    }

    /** Constroi o botao */
    private void configurarBotao() {
        btnBCadProdutos.getStyleClass().add("btnB");
        btnBCadProdutos.setId("btn_mais");
        btnBCadProdutos.setMinSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setMaxSize(cMenu.getLarguraX(), cMenu.getAlturaY());
        btnBCadProdutos.setText("Cadastro De Produtos");
    }

    /** Configura o box com os botoes relacionados */
    private void configurarBox() {
        boxCCadProdutos.setPrefHeight(0);
        boxCCadProdutos.setVisible(false);
    }

    /** Verifica se o box com botoes relacionados esta visivil ou nao */
    private void minimizaMaximiza() {
        if (boxCCadProdutos.isVisible()) {
            minimizarBox();
        } else {
            maximizarBox();
        }
    }

    /** Apaga tudo do box com botoes relacionados */
    public void minimizarBox() {
        boxCCadProdutos.setPrefHeight(0);
        boxCCadProdutos.setVisible(false);
        boxCCadProdutos.getChildren().removeAll(getMenusC());
    }

    /** Adiciona todos os botoes relacionados ao box */
    private void maximizarBox() {
        boxCCadProdutos.setPrefHeight(Control.USE_COMPUTED_SIZE);
        boxCCadProdutos.setVisible(true);
        boxCCadProdutos.getChildren().addAll(getMenusC());
    }

    /** Todos os botoes secundarios
     * @return VBox[] - menus C */
    private VBox[] getMenusC() {
        return new VBox[] {
                cCadDepartamentos.getMenuC(),
                cCadGrupos.getMenuC(),
                cCadSubgrupos.getMenuC(),
                cCadProdutos.getMenuC()
        };
    }

    /** Liberacao para o menu */
    private void liberacaoParaBotao() {
        // VALIDACAO DE LIBERACOES DE USUARIO
        vLiberacao.liberacaoUsuario(btnBCadProdutos, Liberacoes.CADASTROS_PRODUTOS, boxDoMenu);
    }
}
