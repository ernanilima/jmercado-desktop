package br.com.ernanilima.jmercado.service.validacao;

import br.com.ernanilima.jmercado.service.componente.Legenda;
import br.com.ernanilima.jmercado.service.constante.MensagemErro;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCampo {

    @Autowired private Legenda legenda;

    /** Valida campo passado no parametro, verifica se campo eh vazio
     * Caso o campo seja vazio, um alerta eh exibido.
     * @param campo - campo a ser validado
     * @param tituloCampo - titulo do campo a ser validado
     * @return boolean - true se tudo ok com o campo passado no parametro */
    public boolean campoVazio(Node campo, Label tituloCampo) {
        String s = "";
        if (campo instanceof TextField) {
            s = ((TextField) campo).getText();
        }
        if (s.equals("") || s.equals("0")) {
            legenda.exibeAlerta(MensagemErro.campoObrigatorio(tituloCampo.getText()), campo);
            return false;
        }
        return true;
    }
}
