package br.com.ernanilima.jmercado.service.componente;

import br.com.ernanilima.jmercado.service.constante.enums.TipoAtalho;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.stereotype.Component;

@Component
public class Atalho {

    /** Filtra todas as teclas pressionadas na Janela
     * Usado para obter atalho pressionado
     * @param event KeyEvent - teclas pressionadas
     * @return Atalho - tipo de atalho solicitado */
    public static TipoAtalho getAtalhoSolicitado(KeyEvent event) {

        if (event.getCode() == KeyCode.F && event.isControlDown()) {
            return TipoAtalho.PESQUISAR;

        } else if (event.getCode() == KeyCode.N && event.isControlDown()) {
            return TipoAtalho.CADASTRAR;

        } else if (event.getCode() == KeyCode.E && event.isControlDown()) {
            return TipoAtalho.EDITAR;

        } else if (event.getCode() == KeyCode.DELETE && event.isControlDown()) {
            return TipoAtalho.EXCLUIR;

        } else if (event.getCode() == KeyCode.S && event.isControlDown()) {
            return TipoAtalho.GRAVAR;

        } else if (event.getCode() == KeyCode.ESCAPE && event.isControlDown()) {
            return TipoAtalho.CANCELAR;
        } return null;
    }
}
