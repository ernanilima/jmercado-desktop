package br.com.ernanilima.jmercado.liberacao.validacao;

import br.com.ernanilima.jmercado.controller.LoginController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.TipoLiberacao;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidarLiberacao {

    @Autowired private LoginController cLogin;

    private Set<Liberacoes> lsLiberacoesUsuario;

    /** Verifica se o usuario tem a permissao solicitada
     * @param btnComValidacao Node - botao/campo com validacao
     * @param liberacaoNecessaria Liberacoes - liberacao solicitada */
    public void liberacaoUsuario(Node btnComValidacao, Liberacoes liberacaoNecessaria) {
        if (cLogin.getUsuarioAtual() != null) {
            lsLiberacoesUsuario = (cLogin.getUsuarioAtual().getTipoLiberacao() == TipoLiberacao.USUARIO ?
                    cLogin.getUsuarioAtual().getLiberacoes() :
                    cLogin.getUsuarioAtual().getMGrupoUsuario().getLiberacoes());

            btnComValidacao.setDisable(!validarLiberacao(lsLiberacoesUsuario, liberacaoNecessaria));
        }
    }

    /** Verifica se o usuario tem a permissao solicitada
     * @param lsLiberacoesUsuario Set<Liberacoes> - liberacoes do usuario
     * @param liberacaoNecessaria Liberacoes - liberacao solicitada
     * @return boolean - true se usuario tiver a liberacao solicitada */
    private boolean validarLiberacao(Set<Liberacoes> lsLiberacoesUsuario, Liberacoes liberacaoNecessaria) {
        return lsLiberacoesUsuario.stream()
                .anyMatch(list -> String.valueOf(list.getCodigo()).equals(String.valueOf(liberacaoNecessaria.getCodigo())));
    }
}
