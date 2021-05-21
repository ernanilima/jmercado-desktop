package br.com.ernanilima.jmercado.service.validacao;

import br.com.ernanilima.jmercado.service.componente.Legenda;
import br.com.ernanilima.jmercado.service.constante.MensagemAlerta;
import br.com.ernanilima.jmercado.utils.Filtro;
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
        String s = textoCampoParametro(campo);
        if (s.trim().equals("")) {
            legenda.exibirAlerta(MensagemAlerta.campoObrigatorio(tituloCampo.getText()), campo);
            return false;
        }
        return true;
    }

    /** Valida campo passado no parametro, verifica se campo tem um codigo
     * Caso o campo nao tenha um inteiro (codigo), um alerta eh exibido.
     * @param campo - campo a ser validado
     * @param tituloCampo String - titulo do campo a ser validado
     * @param campoMensagemErro Label - campo para exibir mensagem de erro
     * @return boolean - true se tudo ok com o campo passado no parametro */
    public boolean loginCodigoVazio(Node campo, String tituloCampo, Label campoMensagemErro) {
        String s = textoCampoParametro(campo);
        if (Filtro.pInt(s) <= 0) {
            legenda.exibirAlerta(MensagemAlerta.campoObrigatorio(tituloCampo), campo, campoMensagemErro);
            return false;
        }
        return true;
    }

    /** Valida campo passado no parametro, verifica se campo
     * nao tem a quantidade de caractere(s) solicitado
     * @param campo Node - campo a ser validado
     * @param tituloCampo String - titulo do campo a ser validado
     * @param numCaracteres int - quantidade de caractere(s)
     * @param campoMensagemErro Label - campo para exibir mensagem de erro
     * @return boolean - true se tudo ok com o campo passado no parametro */
    public boolean loginSenhaVaziaInvalida(Node campo, String tituloCampo, int numCaracteres, Label campoMensagemErro) {
        String s = textoCampoParametro(campo);
        if (s.length() < numCaracteres) {
            legenda.exibirAlerta(MensagemAlerta.minimoCaracteres(numCaracteres, tituloCampo), campo, campoMensagemErro);
            return false;
        }
        return true;
    }

    /** Valida se senhas sao iguais
     * @param campoSenha1 Node - campo da senha1 para ser comparada
     * @param campoSenha2 Node - campo da senha2 para ser comparada
     * @param campoMensagemErro Label... - campo(s) para exibir a mensagem de erro
     * @return boolean - true se senhas combinarem */
    public boolean loginSenhasIguais(Node campoSenha1, Node campoSenha2, Label... campoMensagemErro) {
        String senha1 = textoCampoParametro(campoSenha1);
        String senha2 = textoCampoParametro(campoSenha2);
        if (!senha1.equals("") && !senha1.equals(senha2)) {
            legenda.exibirAlerta(MensagemAlerta.SENHA_NAO_COMBINAM, campoSenha1, campoMensagemErro);
            return false;
        }
        return true;
    }

    /** Verifica o tipo do campo e captura o texto do campo
     * @param campo Node - campo a ser validado
     * @return String - texto do campo */
    private String textoCampoParametro(Node campo) {
        String textoNoCampo = "";
        if (campo instanceof TextField) {
            textoNoCampo = ((TextField) campo).getText();
        }
        return textoNoCampo;
    }
}
