package br.com.ernanilima.jmercado.liberacao.validacao;

import br.com.ernanilima.jmercado.controller.LoginController;
import br.com.ernanilima.jmercado.liberacao.Liberacoes;
import br.com.ernanilima.jmercado.liberacao.TipoLiberacao;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidarLiberacao {

    @Autowired private LoginController cLogin;

    private Set<Liberacoes> lsLiberacoesUsuario;

    /** Verifica se o usuario tem a permissao solicitada
     * Usado principalmente em controller
     * @param btnComValidacao Node - botao/campo com validacao
     * @param liberacaoNecessaria Liberacoes - liberacao solicitada */
    public void liberacaoUsuario(Node btnComValidacao, Liberacoes liberacaoNecessaria) {
        this.liberacaoUsuario(btnComValidacao, liberacaoNecessaria, null);
    }

    /** Verifica se o usuario tem a permissao solicitada
     * Usado principalmente em menu lateral
     * @param btnComValidacao Node - botao/campo com validacao
     * @param liberacaoNecessaria Liberacoes - liberacao solicitada
     * @param vBoxDoBtn VBox - box onde o botao esta */
    public void liberacaoUsuario(Node btnComValidacao, Liberacoes liberacaoNecessaria, VBox vBoxDoBtn) {
        if (cLogin.getUsuarioAtual() != null) {
            lsLiberacoesUsuario = (cLogin.getUsuarioAtual().getTipoLiberacao() == TipoLiberacao.USUARIO ?
                    cLogin.getUsuarioAtual().getLiberacoes() :
                    cLogin.getUsuarioAtual().getMGrupoUsuario().getLiberacoes());

            boolean validacaoLiberacaoRealizada = validarLiberacao(lsLiberacoesUsuario, liberacaoNecessaria);
            btnComValidacao.setDisable(!validacaoLiberacaoRealizada);

            validacaoParaMenu(vBoxDoBtn, validacaoLiberacaoRealizada, btnComValidacao);
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

    /** Realiza verificacao para exibir o menu no box de menu lateral
     * @param vBoxDoBtn VBox - box onde o botao para vericicacao esta
     * @param validacaoLiberacaoRealizada boolean - resultado da validacao de liberacao
     * @param btnComValidacao Node - botao de verificacao */
    private void validacaoParaMenu(VBox vBoxDoBtn, boolean validacaoLiberacaoRealizada, Node btnComValidacao) {
        if (vBoxDoBtn != null && !validacaoLiberacaoRealizada) {
            // se botao estiver em um vbox e o usuario nao tiver liberacao
            // para visualizar o botao, o mesmo eh removido do vbox
            vBoxDoBtn.getChildren().remove(btnComValidacao);
        }
    }
}
